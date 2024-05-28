# Gestor de Tareas Backend

La aplicación que estamos desarrollando es un **Gestor de Tareas Backend**, diseñado para administrar y coordinar tareas dentro de proyectos específicos. Esta aplicación está construida utilizando **Spring Boot** y se ha integrado con seguridad basada en **JSON Web Tokens (JWT)** para manejar la autenticación y autorización de usuarios.

## Funcionalidades Clave de la Aplicación

### Gestión de Usuarios:
- Los usuarios pueden registrarse, iniciar sesión y gestionar su perfil.
- Cada usuario tiene asignado un rol (como `ADMIN` o `USER`), que determina sus permisos dentro de la aplicación.

### Roles y Permisos:
- **ADMIN**: Puede acceder a funciones administrativas, como gestionar todos los proyectos, tareas, y usuarios.
- **USER**: Puede gestionar sus propias tareas y proyectos asignados.

### Gestión de Proyectos:
- Los usuarios pueden crear, modificar, y eliminar proyectos, dependiendo de sus roles.
- Cada proyecto puede contener múltiples tareas.

### Gestión de Tareas:
- Las tareas se pueden crear dentro de proyectos. Cada tarea tiene atributos como estado, prioridad, y fechas de vencimiento.
- Los usuarios pueden actualizar y monitorear el progreso de sus tareas.

### Autenticación y Seguridad:
- La aplicación utiliza JWT para manejar la autenticación de usuarios. El token JWT asegura que las solicitudes al servidor están autorizadas.
- Las contraseñas de los usuarios se almacenan de forma segura utilizando hashing.

### API RESTful:
- La aplicación expone una API RESTful que permite a los clientes interactuar con el sistema de gestión de tareas de manera programática.
- Los endpoints de la API soportan operaciones CRUD (Crear, Leer, Actualizar, Eliminar) para proyectos y tareas.

## Uso Práctico

El sistema es ideal para equipos que necesitan una herramienta para gestionar tareas y proyectos de manera eficiente, asegurando que todos los miembros del equipo tengan acceso a la información más actualizada sobre sus responsabilidades y los plazos de las tareas. Puede ser utilizado en entornos corporativos, por startups, o incluso por grupos de estudiantes en entornos académicos para organizar sus proyectos y tareas asociadas.

La aplicación asegura que solo los usuarios autorizados puedan acceder a la información sensible y realizar cambios, mejorando la seguridad y la integridad de la gestión de proyectos y tareas.
