# 🧾 Gestión de Ordenes (ms-orders): 
Este proyecto es un microservicio REST desarrollado con **Spring Boot 3**, que permite gestionar órdenes de compra relacionadas con productos.
## 🚀 Cómo correr el proyecto
#Paso 1: Clona el repositorio:
   ```bash
   #git clone https://github.com/tu_usuario/msOrders.git
   #cd msOrders

##Paso 2: Configura la base de datos:
#Crea la base de datos db_orders en MySQL:

##Paso 3: Ejecuta la aplicación:
#Puede ser desde la terminal con mvn spring-boot:run o presionando run desde MsProductsApplication.

##Paso 4: Accede a la API: El microservicio estará disponible en http://localhost:8082.

##Endpoints principales
#GET /api/orders
#POST /api/orders

##Patrones de diseño aplicados
#Circuit Breaker
#Uso de Resilience4j con @CircuitBreaker y fallbackMethod al consultar el microservicio de productos para verificar stock.

#Builder
#Utilizado en la entidad Order:

#Validaciones y manejo de errores
#@Valid en el controlador para validar datos de entrada.
#@ControllerAdvice con @ExceptionHandler para devolver respuestas con códigos adecuados (400, 404).
