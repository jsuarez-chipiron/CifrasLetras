# CifrasLetras

Un programa que calcula mediante un indice de oracle text palabras que contienen ciertas letras indicadas como parámetro

## Crear la tabla e indice en la BBDD de oracle

```
create index ot_cifrasletras on cifrasletras(ordenada) INDEXTYPE IS ctxsys.context;
alter index ot_cifrasletras rebuild;

create table cifrasletras(palabra varchar2(9) not null primary key, ordenada varchar2(9));

insert into cifrasletras_bk (palabra, ordenada) select palabra,ordenada from cifrasletras;

java -cp /home/jasuarez/informatica/java/jars/ojdbc5.jar:ciflet.jar es.javier.Calcular eismoqocs
```

Debido a las politicas de Oracle hay que instalar el jar del driver ojdbc en el repositorio local para que el código funcione.

```
mvn install:install-file -Dfile=ojdbc6.jar -DgroupId=com.oracle -DartifactId=ojdbc6 -Dversion=6 -Dpackaging=jar
```

Luego referenciarlo de esta manera en el pom.xml

```
<dependency>
    <groupId>com.oracle</groupId>
    <artifactId>ojdbc6</artifactId>
    <version>6</version>
</dependency>
```