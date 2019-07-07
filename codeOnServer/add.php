<?php
$servername = "localhost"; //Adres serwera MySQL
$username = "root"; //Nazwa konta użytkownika MySQL
$password = ""; //Hasło do konta użytkownika MySQL
$dbname = "arduino"; //Nazwa bazy danych
 
$conn = new mysqli($servername, $username, $password, $dbname); //Utworzenie połączenia z MySQL

if ($conn->connect_error) { //Sprawdzenie połączenia z MySQL
    die("Connection failed: " . $conn->connect_error); //Wyświetlenie informacji o problemie z połączeniem
}

$a = $_GET["a"]; //Odebranie danych wysłanych przez ESP
$b = $_GET["b"]; //Odebranie danych wysłanych przez ESP

$sql = "INSERT INTO weather (temp_in, temp_out)
 VALUES ('$a', '$b')"; 
 //W pierwszej lini następuje zdefiniowanie nazwy tabeli oraz kolumn do których mają zostać dodane dane
 //a w drugiej definiowanie danych które zostaną dodane to tabeli

 //  Można to polecenie także zapisać w formie jednej lini ale polecam pozostać przy zapisie w dwóch liniach ponieważ jest bardziej przejrzysty
 //  $sql = "INSERT INTO weather (Dane1, Dane2) VALUES ('$a', '$b')"; 
 
if ($conn->query($sql) === TRUE) { //Sprawdzenie czy dane zostały poprawnie dodane do tabeli
    echo "Rekord zostal dodany poprawnie!"; //Wyświetlenie komunikatu o powodzeniu
} else {
    echo "Error: " . $sql . "<br>" . $conn->error; //Wyświetlenie komunikatu o niepowodzeniu wraz z informacjami na temat błędu
}
 
$conn->close(); //Zamknięcie połączenia z MySQL
?>