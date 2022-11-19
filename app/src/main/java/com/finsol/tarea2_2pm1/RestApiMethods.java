package com.finsol.tarea2_2pm1;

public class RestApiMethods
{

    public static final String ipaddress     = "18.188.250.246/";
    public static final String WebAPI        =  "PM01/";
    public static final String CreateEnpoint  =  "CrearAuto.php";
    public static final String ReadEnpoint  =  "listaAutos.php";

    public static final String ApiCreate  =  "http://" + ipaddress + WebAPI + CreateEnpoint;
//    public static final String ApiRead  =  "http://" + ipaddress + WebAPI + ReadEnpoint;
    public static final String ApiRead  =  "https://jsonplaceholder.typicode.com/posts/";

}
