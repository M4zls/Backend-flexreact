# 🚀 Flexreact Backend API

Backend API completo para la aplicación de e-commerce Flexreact. Construido con Node.js, Express y Supabase.

## 📋 Características

- ✅ **Autenticación JWT** con Supabase Auth
- ✅ **Gestión de Productos** (CRUD completo)
- ✅ **Gestión de Pedidos** con historial
- ✅ **Gestión de Usuarios** y perfiles
- ✅ **Middleware de Autenticación** 
- ✅ **Manejo Centralizado de Errores**
- ✅ **CORS** configurado para frontend
- ✅ **Validaciones** de datos

## 🚀 Tecnologías

- Node.js
- Express
- Supabase (Base de datos y autenticación)
- CORS

## 📦 Instalación

```bash
npm install
```

## 🔧 Configuración

Crear archivo `.env` con las siguientes variables:

```
SUPABASE_URL=tu_url_de_supabase
SUPABASE_ANON_KEY=tu_clave_anonima
PORT=8080
NODE_ENV=development
```

### Configurar Base de Datos en Supabase

1. Ve a tu proyecto en [Supabase](https://supabase.com)
2. Abre el **SQL Editor**
3. Ejecuta el script `database/schema.sql`
4. Verifica que las tablas se crearon correctamente

## 🏃‍♂️ Ejecutar en Desarrollo

```bash
npm run dev
```

El servidor correrá en: `http://localhost:8080`

## 📡 Endpoints

### 🔐 Autenticación (`/api/auth`)

| Método | Endpoint | Auth | Descripción |
|--------|----------|------|-------------|
| POST | `/api/auth/register` | ❌ | Registrar nuevo usuario |
| POST | `/api/auth/login` | ❌ | Iniciar sesión |
| POST | `/api/auth/logout` | ✅ | Cerrar sesión |
| GET | `/api/auth/me` | ✅ | Obtener usuario actual |

### 📦 Productos (`/api/productos`)

| Método | Endpoint | Auth | Descripción |
|--------|----------|------|-------------|
| GET | `/api/productos` | ❌ | Listar todos los productos |
| GET | `/api/productos/:id` | ❌ | Obtener producto por ID |
| POST | `/api/productos` | ✅ | Crear producto (Admin) |
| PUT | `/api/productos/:id` | ✅ | Actualizar producto |
| DELETE | `/api/productos/:id` | ✅ | Eliminar producto |

### 🛒 Pedidos (`/api/pedidos`)

| Método | Endpoint | Auth | Descripción |
|--------|----------|------|-------------|
| POST | `/api/pedidos` | ✅ | Crear nuevo pedido |
| GET | `/api/pedidos` | ✅ | Listar pedidos del usuario |
| GET | `/api/pedidos/:id` | ✅ | Obtener pedido específico |
| PATCH | `/api/pedidos/:id/estado` | ✅ | Actualizar estado |

### 👤 Usuarios (`/api/usuarios`)

| Método | Endpoint | Auth | Descripción |
|--------|----------|------|-------------|
| GET | `/api/usuarios/perfil` | ✅ | Obtener perfil del usuario |
| PUT | `/api/usuarios/perfil` | ✅ | Actualizar perfil |

## 🔐 Autenticación

Las rutas protegidas requieren un token JWT en el header:

```
Authorization: Bearer <token>
```

**Ejemplo con fetch:**
```javascript
fetch('http://localhost:8080/api/pedidos', {
  headers: {
    'Authorization': `Bearer ${token}`,
    'Content-Type': 'application/json'
  }
})
```

## 📁 Estructura del Proyecto

```
src/
├── config/         # Configuración (Supabase)
├── controllers/    # Controladores de rutas
├── middleware/     # Middleware (auth, errorHandler)
├── routes/         # Definición de rutas
├── services/       # Lógica de negocio
└── server.js       # Punto de entrada
database/
└── schema.sql      # Script SQL para Supabase
```

## 🔗 Integración con Frontend

En tu frontend (Flexreact), configura:

**.env.local:**
```env
NEXT_PUBLIC_API_URL=http://localhost:8080
```

## 🗄️ Base de Datos

**Tablas principales:**
- `usuarios` - Información de usuarios
- `productos` - Catálogo de productos
- `pedidos` - Pedidos realizados
- `pedido_productos` - Relación pedidos-productos
- `direcciones` - Direcciones de envío

Ver `database/schema.sql` para la estructura completa.

## 📝 Scripts Disponibles

```bash
npm start       # Iniciar en producción
npm run dev     # Iniciar en desarrollo (hot reload)
npm test        # Ejecutar tests (por implementar)
```

## 🐛 Debugging

**Verificar conexión a Supabase:**
- Comprueba las credenciales en `.env`
- Verifica que las tablas existan en Supabase
- Revisa que RLS esté configurado

**Ver logs:**
```bash
npm run dev
```

## 📄 Licencia

ISC
