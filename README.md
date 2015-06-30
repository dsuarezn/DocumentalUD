# DocumentalUD
Dando alcance al correo anterior complemento la información con lo siguiente:

Servidor de aplicaciones utilizado: Apache tomcat 7.0
IDE: netbeans 8.0.1
Base de datos: mysql
en el archivo persistence.xml pueden cambiar la siguiente linea:

\<property name="javax.persistence.jdbc.url" value="jdbc:mysql://localhost:3306/Documental?zeroDateTimeBehavior=convertToNull"/>

por la siguiente linea:

\<property name="javax.persistence.jdbc.url" value="jdbc:mysql://http://200.69.101.174:3306/Documental?zeroDateTimeBehavior=convertToNull"/>

esto con el fin de centralizar la base de datos.

Adjunto el archivo clases.rar donde se encuentran los .jar utilizados

y el siguiente link para observar el tutorial para sincronizar el repositorio con netbeans

https://lineaporlinea.wordpress.com/2014/01/19/integrar-netbeans-con-github/
