## Escuela Colombiana de Ingeniería

## Arquitecturas de Software

## Emily Noreña y Haider Rodriguez

# Componentes y conectores - Parte I.

El ejercicio se debe traer terminado para el siguiente laboratorio (Parte II).

#### Middleware- gestión de planos.


## Antes de hacer este ejercicio, realice [el ejercicio introductorio al manejo de Spring y la configuración basada en anotaciones](https://github.com/ARSW-ECI/Spring_LightweightCont_Annotation-DI_Example).

En este ejercicio se va a construír un modelo de clases para la capa lógica de una aplicación que permita gestionar planos arquitectónicos de una prestigiosa compañia de diseño. 

**Solución: Ejercicio introductorio al manejo de Spring y la configuración basada en anotaciones**

En las clases `SpanishSpellChecker` y `EnglishSpellChecker` incluimos la anotación `@Component("<nombre_del_bean>")`. Esta anotación nos permite marcar estas clases como un bean administrado por Spring. Una vez detectada, se registra en el contenedor de Spring para poder inyectarla. El valor dentro de la anotación corresponde a el nombre del bean, sirve para distinguir beans del mismo tipo.

<p>
  <img src="Introduccion/img/spanishSpellChecker.png" width="570"/><br>
</p>
<p>
  <img src="Introduccion/img/englishSpellChecker.png" width="400"/><br>
</p>

En la clase `GrammarChecker`, añadimos la anotación `@Component`, `@Autowired` y `@Qualifier("englishSpellChecker")`. 

`@Autowired`: Le dice a Spring que debe inyectar automáticamente, en este caso SpellChecker. Aquí se puede presentar un conflicto de dependencias. Para esto, usamos `@Qualifier("<nombre_del_bean>")`. Esta anotación resuelve ese conflicto cuando hay múltiples implementaciones del mismo tipo.

<p>
  <img src="Introduccion/img/GrammarChecker.png" width="270"/><br>
</p>


Salidas:
<p>
  <img src="Introduccion/img/output1.png" width="270"/><br>
</p>
<p>
  <img src="Introduccion/img/output2.png" width="270"/><br>
</p>


![](img/ClassDiagram1.png)

1. Configure la aplicación para que funcione bajo un esquema de inyección de dependencias, tal como se muestra en el diagrama anterior.


	Lo anterior requiere:

	* Agregar las dependencias de Spring.
	* Agregar la configuración de Spring.
	* Configurar la aplicación -mediante anotaciones- para que el esquema de persistencia sea inyectado al momento de ser creado el bean 'BlueprintServices'.


2. Complete los operaciones getBluePrint() y getBlueprintsByAuthor(). Implemente todo lo requerido de las capas inferiores (por ahora, el esquema de persistencia disponible 'InMemoryBlueprintPersistence') agregando las pruebas correspondientes en 'InMemoryPersistenceTest'.

3. Haga un programa en el que cree (mediante Spring) una instancia de BlueprintServices, y rectifique la funcionalidad del mismo: registrar planos, consultar planos, registrar planos específicos, etc.

4. Se quiere que las operaciones de consulta de planos realicen un proceso de filtrado, antes de retornar los planos consultados. Dichos filtros lo que buscan es reducir el tamaño de los planos, removiendo datos redundantes o simplemente submuestrando, antes de retornarlos. Ajuste la aplicación (agregando las abstracciones e implementaciones que considere) para que a la clase BlueprintServices se le inyecte uno de dos posibles 'filtros' (o eventuales futuros filtros). No se contempla el uso de más de uno a la vez:
	* (A) Filtrado de redundancias: suprime del plano los puntos consecutivos que sean repetidos.
	* (B) Filtrado de submuestreo: suprime 1 de cada 2 puntos del plano, de manera intercalada.

5. Agrege las pruebas correspondientes a cada uno de estos filtros, y pruebe su funcionamiento en el programa de prueba, comprobando que sólo cambiando la posición de las anotaciones -sin cambiar nada más-, el programa retorne los planos filtrados de la manera (A) o de la manera (B). 
