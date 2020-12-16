package com.tempvic.weather.utils;

public class TempConvert {
    public String celsiusToFahrenheit(double celsius) {
        double convert = (celsius * 9 / 5) + 32;
        return String.valueOf(convert);
    }

    public String celsiusToKelvin(double celsius) {
        double convert = celsius + 273.15;
        return String.valueOf(convert);
    }

/*    public String fahrenheitToCelsius(double fahrenheit) {
        double convert = (fahrenheit - 32) * 5 / 9;
        return String.valueOf(convert);
    }

    public String fahrenheitToKelvin(double fahrenheit) {
        double convert = (fahrenheit - 32) * 5 / 9 + 273.15;
        return String.valueOf(convert);
    }

    public String kelvinToCelsius(double kelvin) {
        double convert = kelvin - 273.15;
        return String.valueOf(convert);
    }

    public String kelvinToFahrenheit(double kelvin) {
        double convert = (kelvin - 273.15) * 9 / 5 + 32;
        return String.valueOf(convert);
    }*/
}
