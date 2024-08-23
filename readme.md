# Makersharks Supplier Query API

This project is a solution of Makersharks Supplier Query API assignment.

## Project Setup

This project uses Docker Compose to run all necessary services. Follow the instructions below to get started.

### Prerequisites

Ensure you have Docker and Docker Compose installed on your system.

- [Docker Installation Guide](https://docs.docker.com/get-docker/)
- [Docker Compose Installation Guide](https://docs.docker.com/compose/install/)

### Running the Project

1. **Clone the Repository:**
   ```bash
   git clone https://github.com/gauravofficial133/Makersharks-Assessment.git
   ```

2. **Navigate to the Project Directory:**
   ```bash
   cd supplier-details-service
   ```

3. **Start the Services:**
   Run the following command to start the project:
   ```bash
   docker-compose up -d
   ```

   The `-d` flag runs the containers in detached mode.

4. **Accessing the Application:**
   The application will be accessible at `http://localhost:8080`. Check your Docker Compose file for the exact port mapping.

### Stopping the Services

To stop the running services, use:
```bash
docker-compose down
```

### Additional Commands

- **Check Logs:**  
  You can view the logs using:
  ```bash
  docker-compose logs -f
  ```

### Authentication

Both the `/query` and `/create` APIs are protected and require authentication via a JWT token. You can obtain the token by making a POST request to `/login` with the following request body:

```json
{
  "username": "Gaurav",
  "password": "Test@123"
}
```

This is the default username and password.

### API Usage with cURL

Below are the cURL commands to consume the `/login`, `/api/supplier/query`, and `/api/supplier/create` endpoints.

1. **Obtain JWT Token:**

   ```bash
   curl -X POST http://localhost:<port>/login \
   -H "Content-Type: application/json" \
   -d '{
     "username": "Gaurav",
     "password": "Test@123"
   }'
   ```

   The token will be returned in the response and should be used in the Authorization header for the following requests.

2. **Search for Manufacturers by Location, Business Type, and Process:**

   ```bash
   curl -X POST http://localhost:<port>/api/supplier/query \
   -H "Content-Type: application/json" \
   -H "Authorization: Bearer <your-jwt-token>" \
   -d '{
     "location": "India",
     "natureOfBusiness": "small_scale",
     "manufacturingProcess": "3d_printing",
     "page": 0,
     "size": 10
   }'
   ```

3. **Create a New Supplier:**

   ```bash
   curl -X POST http://localhost:<port>/api/supplier/create \
   -H "Content-Type: application/json" \
   -H "Authorization: Bearer <your-jwt-token>" \
   -d '{
     "companyName": "New Manufacturer",
     "website": "https://newmanufacturer.com",
     "location": "India",
     "natureOfBusiness": "small_scale",
     "manufacturingProcesses": ["moulding", "casting"]
   }'
   ```

### API Documentation

The API is documented using Swagger. Once the application is running, you can access the Swagger UI at:
```
http://localhost:8080/swagger-ui.html
```

### Troubleshooting

- Ensure all dependencies are correctly set up.
- Verify that the necessary ports are not blocked by your system's firewall.

