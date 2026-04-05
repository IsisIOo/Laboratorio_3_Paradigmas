# Laboratorio 3 – Paradigmas de Programación (Java)

Proyecto del **Laboratorio 3** del curso **Paradigmas de Programación**, implementado en **Java**.  
El objetivo del laboratorio es modelar un **sistema de archivos** (estilo consola) utilizando **Programación Orientada a Objetos**, con soporte para:

- creación de un “System” (filesystem)
- administración de **drives** (unidades)
- registro / login / logout de **usuarios**
- manejo de **rutas** (paths) y navegación (`cd`)
- creación de **carpetas** (`mkdir`)
- creación, eliminación, copia y movimiento de **archivos**
- operaciones tipo `del`, `copy`, `move` desde un menú interactivo

> El programa incluye un **menú por consola** (`Menu_...java`) que guía al usuario para ejecutar las operaciones.

---

## Tecnologías utilizadas

- **Lenguaje:** Java  
- **Paradigma:** Programación Orientada a Objetos (POO)
- **Entrada/Salida:** Consola (uso de `Scanner`)
- **Estructuras de datos:** `List`, `ArrayList`, Streams (`stream()`, `map`, `filter`, `collect`)
- **IDE / proyecto:** estructura típica de IntelliJ (`.idea`, archivo `.iml`, carpeta `out/`)

---

## Estructura del repositorio

Clases principales:

- `Main.java`  
  Punto de entrada. Crea el menú y ejecuta el loop principal.

- `Menu_21168603_ioyanedelalvarez.java`  
  Menú por consola. Permite:
  1) Crear sistema  
  2) Modificar sistema (operaciones)  
  3) Ver el sistema  
  4) Salir  

- `System_21168603_ioyanedelalvarez.java`  
  Clase central que modela el filesystem. Maneja drives, usuarios, usuario logueado, drive actual y el historial/estado de rutas.

- `Path_21168603_ioyanedelalvarez.java`  
  Representa una ruta actual: string tipo `c:/folder1/`, lista de carpetas, lista de archivos, usuario asociado y timestamp.

Otras entidades:
- `Drive_21168603_ioyanedelalvarez.java` (unidad)
- `Usuario_21168603_ioyanedelalvarez.java` (usuario)
- `File_21168603_ioyanedelalvarez.java` (archivo)
- `Chapter_21168603_ioyanedelalvarez.java` (carpeta)
- Interfaces: `Interfaz_*`
  
Documentación/diagramas:
- `Diagrama_final.png`
- `prediccion_diagrmaUML.jpeg`
- `Informe_lab3_21168603_OyanedelAlvarez.pdf.pdf`

---

## Uso del programa (menú)

Al iniciar, el sistema entra a un loop (mientras no se elija “Salir”) y muestra opciones.

### Menú principal
- **1. Crear Sistema** → pide el nombre y crea la instancia de `System_...`
- **2. Modificar el Sistema** → muestra el menú de operaciones
- **3. Ver el Sistema** → imprime el estado actual (`toString()`)
- **4. Salir**

### Menú “Modificar el sistema”
Operaciones (según `Menu_...java`):

1. Agregar un drive  
2. Crear un usuario (register)  
3. Login usuario  
4. Logout usuario  
5. Fijar drive actual (switchDrive)  
6. Crear directorio (mkdir)  
7. Cambiar de directorio (cd)  
8. Agregar archivo (creación interactiva)  
9. Eliminar archivo o carpeta (del)  
10. Copiar (copy)  
11. Mover (move)

---

## Modelo y comportamiento (resumen)

### Clase `System_...`
Guarda y gestiona:
- `List<Drive>` drives del sistema
- `List<Usuario>` usuarios registrados
- `List<String> logueados` (en tu implementación se usa como lista; normalmente es 0 o 1 usuario activo)
- `List<String> actual` drive actual (también lista, normalmente 1 elemento)
- `List<Path>` rutas (historial/estado de navegación)

Incluye métodos como:
- `addDrive(letter, name, capacity)` (valida letra única)
- `register(username)` (usuario único)
- `login(username)` (solo si existe y no hay sesión activa)
- `logout()`
- `switchDrive(letter)` (solo si hay sesión iniciada)
- `mkdir(folderName)`
- `cd(path)` con soporte para:
  - `"."`, `"./"` (mantenerse)
  - `".."` (retroceder)
  - `"/"` (volver a la raíz de la unidad)
  - entrar por nombre de carpeta existente
  - casos con prefijo `"./carpeta"`

Y operaciones tipo:
- `crea_file()` 
- `addFile(file)`
- `del(patternOrName)`
- `copy(source, target)`
- `move(source, target)`

### Clase `Path_...`
Representa el estado de una ruta:
- `rutaSTRING` (ej: `"c:/"`, `"c:/folder1/"`, etc.)
- `List<Chapter>` carpetas
- `List<File>` archivos
- usuario asociado a la ruta
- fecha de creación
