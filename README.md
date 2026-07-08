## Estudiantes

1. Bastian Ignacio Molina Contreras
2. Isaac Amadeus Nelson Castro Villalobos
3. Martin Ignacio Carrasco Perez

## Tema 2:  Simulador de Tienda de Mascotas Virtual 

Este simulador ofrecerá al usuario la experiencia de gestionar su propia tienda de mascotas virtual. El jugador comenzará con un presupuesto inicial para adquirir diferentes tipos de mascotas (ej. perros, gatos, peces, pájaros), cada una con atributos y necesidades específicas como alimentación (tipo de comida, frecuencia), higiene, nivel de felicidad, tipo de recinto y salud. El usuario deberá gestionar el inventario de mascotas y de suministros (comida, medicinas). Las interacciones incluirán alimentar a las mascotas, limpiar sus hábitats, jugar con ellas para mantener su felicidad, y atender su salud. El estado de cada mascota deberá ser visible y cambiará según los cuidados recibidos. El sistema también simulará la llegada de clientes virtuales interesados en adoptar mascotas, permitiendo al jugador venderlas y así obtener ingresos para continuar gestionando y expandiendo la tienda.

## Diagrama de casos de uso

<img width="990" height="711" alt="Diagrama" src="https://github.com/user-attachments/assets/a9248976-dc66-4c61-b096-923ec179b419" />

## Diagrama de clases

<img width="3055" height="2434" alt="proyecto" src="https://github.com/user-attachments/assets/8cdfae62-ea73-4780-9951-0626bea6ea11" />

## Prototipo Interfaz

<img width="1366" height="774" alt="WhatsApp Image 2026-06-10 at 23 29 48" src="https://github.com/user-attachments/assets/a8114a79-f069-46f6-8541-d00c99f59445" />
<img width="1366" height="768" alt="WhatsApp Image 2026-06-10 at 23 29 48(2)" src="https://github.com/user-attachments/assets/1f07bc16-7b70-42fc-91af-bbeb8796d865" />
<img width="1366" height="768" alt="WhatsApp Image 2026-06-10 at 23 29 48(1)" src="https://github.com/user-attachments/assets/b2a7b19a-5d19-4c8a-9b21-09bdffee6056" />

## Imagenes de la aplicacion

<img width="714" height="712" alt="image" src="https://github.com/user-attachments/assets/6cc6c203-db69-4466-9be5-680847d03da2" />
<img width="714" height="712" alt="image" src="https://github.com/user-attachments/assets/598a09a2-ca12-4922-a751-4004afe556bb" />
<img width="711" height="714" alt="image" src="https://github.com/user-attachments/assets/28a7b24f-b7d2-4ae9-aec4-d5938a15ecda" />


Un simulador de tienda de mascotas virtuales, entre estas tenemos distintos tipos de mascotas tales como perros, gatos, pájaros y peces, con interacciones como alimentarlos, cuidarlos, pasar tiempo con ellos y limpiar su hábitat, también podemos comprar y vender mascotas a clientes virtuales que aparecerán de vez en cuando en nuestra tienda y como última funcionalidad es la gestión de los alimentos y medicamentos que posee la misma tienda.

## Decisiones importantes

Se tomó la decisión de que el cliente virtual también sea capaz de vender mascotas a la tienda y no solo pueda comprar con esto dando más realismo a los propios clientes, también se tomó la decisión de juntar el inventario con el apartado de gestionar la tienda dado que era más lógico y cómodo así, otra decisión importante fue que la clase Perro tiene la opción de definir una raza de este mismo también aportando más realismo a una mascota , se tomó la decisión de crear una clase enum para diferenciar a las mascotas entre sí y poder identificarlas para poder venderlas o comprarlas a los clientes virtuales, así facilitando esto. 

## Patrones usados

-Patrón State: Se utilizó para el comportamiento de la mascota, verificando el estado de esta en el paso del tiempo, así evitando hacer cadenas de if o case dentro de la misma clase, se ocupa en las clases: Mascota, EstadoMascota y subclases de EstadoMascota. 

-Patrón Observer: Se implementó para que cualquiera que quiera ver los estados de las mascotas sea informado automáticamente, este patrón se ocupa en: Mascotas, MascotasObserver y V3_Mascotas. 

-Patrón Template Method: Se implementó para reutilizar el algoritmo de pasar tiempo en las mascotas, cada animal define cuando se desgasta. Se utiliza en: Mascotas y subclases de Mascotas. 

## Autocrítica

El proyecto tiene varios aspectos de mejora qué no lograron ser implementados, se tenían varias ideas, por ejemplo tener distintos tipos de alimentos y medicamentos para las diferentes mascotas o estados qué este tuviera, con eso se podía agregar distintos estados de enfermedad para la mascota y que tuvieran su respectivo medicamento, también el hecho de que las otras mascotas también contarán con sus respectivas razas, otra implementación descartada pero interesante para nuestro proyecto  poder era comprar juguetes para las mascotas y que estas al recibirlas aumentará su felicidad. No hubo grandes problemas dentro del desarrollo dado que la mayoría de estos fueron solucionados gracias a los mismos patrones implementados. Dentro de todo, creemos que logramos enfrentar y sobrellevar de buena forma el proyecto como equipo, dividiendo de buena forma cada parte entre nosotros, teniendo buena comunicación y logrando realizar un muy buen acercamiento a lo que como equipo esperábamos lograr.


