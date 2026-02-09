# 📝 Wordle Solver - Edición Española

Un motor de resolución para **Wordle** desarrollado en Java, diseñado para optimizar la búsqueda de palabras en español mediante análisis estadístico de frecuencias y filtrado dinámico por expresiones regulares.

Este proyecto no solo resuelve el juego, sino que implementa un sistema de **scoring** para recomendar siempre la palabra con mayor probabilidad de éxito según el estado actual del diccionario.

## 🚀 Características Técnicas

* **Análisis Estadístico:** El sistema realiza un estudio de frecuencias posicionales sobre un corpus de palabras en español.
* **Motor de Recomendación:** Implementa un algoritmo de puntuación que clasifica las palabras candidatas basándose en la probabilidad de aparición de sus letras.
* **Filtrado por RegEx:** Generación dinámica de expresiones regulares complejas para reducir el espacio de búsqueda tras cada intento.
* **Soporte Multi-idioma (Extensible):** Diseñado para manejar caracteres especiales como la **Ñ**.
* **Pre-procesamiento de Datos:** Incluye herramientas para limpiar y normalizar diccionarios extensos a formatos optimizados (5 letras).

## 🧠 Lógica de Ingeniería

### 1. Estudio de Frecuencia Posicional
En lugar de una búsqueda aleatoria, el solver utiliza la clase `DataStudy` para calcular la frecuencia de cada letra $L$ en una posición $i$ específica.

La puntuación de una palabra $W$ se define como:
$$Score(W) = \sum_{i=0}^{4} Frequency(Letter_i, Position_i)$$

### 2. Filtrado Dinámico
El sistema utiliza tres estados de retroalimentación para construir una RegEx que filtra el `ArrayList` de palabras en tiempo real:
* **Letras Ausentes:** Cláusulas de *negative lookahead* `(?!.*[letras])`.
* **Letras Presentes (Posición incorrecta):** Cláusulas de *positive lookahead* `(?=.*[letra])`.
* **Letras Correctas:** Posicionamiento directo en el patrón de búsqueda.

## 🛠️ Estructura del Software

* **`WordleSolver.java`**: Controlador principal de la lógica de juego e interfaz de usuario.
* **`ScoreGenerator.java`**: Motor encargado de asignar pesos a las palabras candidatas.
* **`DataStudy.java`**: Capa de análisis que genera las estadísticas a partir del diccionario.
* **`DataProccess.java`**: Script de utilidad para la limpieza de datos (`es.txt` -> `5letters.txt`).
* **`Alphabet.java`**: Enum que gestiona la representación del abecedario español (27 caracteres).

## 💻 Guía de Uso

1.  Ejecuta la clase `Main.java`.
2.  El programa te sugerirá la mejor palabra inicial basada en estadísticas (por ejemplo: *AEREO*).
3.  Introduce el resultado de Wordle en la consola usando esta sintaxis:
    * `.` : Letra no existe (Gris).
    * `?` : Letra existe pero posición incorrecta (Amarillo).
    * `letra` : Letra en posición correcta (Verde).

**Ejemplo de entrada:**
Si la palabra es "CASAS" y pruebas "COSA", introducirías: `C.SA.`

## 🛠 Tecnologías Utilizadas

* **Lenguaje:** Java 17+
* **Gestión de Datos:** Java Streams y Collections API.
* **Algoritmia:** Pattern Matching (RegEx) y Estadística descriptiva.

---
Desarrollado por Daniel Lailin
