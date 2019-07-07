#include <ESP8266WiFi.h>
#include <ESP8266WiFiMulti.h>
#include <ESP8266HTTPClient.h>
ESP8266WiFiMulti WiFiMulti;

#include <OneWire.h>
#include <DallasTemperature.h>
#define ONE_WIRE_BUS 2
OneWire oneWire(ONE_WIRE_BUS);
DallasTemperature sensors(&oneWire);
DeviceAddress thermometerIn = {0x28, 0x79, 0xDA, 0x5B, 0x5, 0x0, 0x0, 0x8A };
DeviceAddress thermometerOut = {0x28, 0xAA, 0x5A, 0x6C, 0x3C, 0x14, 0x1, 0xCB };

void setup() {
  sensors.begin();
  WiFiMulti.addAP("ZTE-201017", "XXX");
  while ((WiFiMulti.run() != WL_CONNECTED)) {
    delay(200);
  }
}

void loop() {
    if ((WiFiMulti.run() == WL_CONNECTED)) {
      sensors.requestTemperatures();
      HTTPClient http;
      String content = "http://192.168.8.100/wifi_test/add.php?a=";
      float tempIn = sensors.getTempC(thermometerIn);
      int tempIn1 = tempIn;
      int tempIn2 = (tempIn - tempIn1) * 100;
      
      content += tempIn1;
      content += ".";
      content += tempIn2;

      content += "&b=";

      float tempOut = sensors.getTempC(thermometerOut);
      int tempOut1 = tempOut;
      int tempOut2 = (tempOut - tempOut1) * 100;
      
      content += tempOut1;
      content += ".";
      content += tempOut2;
      
      http.begin(content);
      int httpCode = http.GET();
      if (httpCode > 0) {
        
        if (httpCode == HTTP_CODE_OK) {
          String payload = http.getString();
        }
      }
      else {
        Serial.printf("[HTTP] GET... failed, error: %s\n", http.errorToString(httpCode).c_str());
      }
      http.end();
    }
  delay(5*1000*60);
}
