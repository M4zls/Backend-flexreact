# 🚀 Guía de Despliegue en Render

## 📋 Prerrequisitos
- Cuenta en [Render](https://render.com)
- Base de datos PostgreSQL (Supabase o Render PostgreSQL)
- Repositorio en GitHub

## 🔧 Pasos para Desplegar

### 1️⃣ Crear Nuevo Web Service
1. Ve a [Render Dashboard](https://dashboard.render.com/)
2. Haz clic en **"New +"** → **"Web Service"**
3. Conecta tu repositorio de GitHub
4. Selecciona el repositorio `Backend-flexreact`

### 2️⃣ Configuración del Servicio
- **Name**: `flexreact-backend`
- **Region**: Selecciona la más cercana (ej: Oregon USA)
- **Branch**: `main`
- **Runtime**: `Docker`
- **Dockerfile Path**: `./dockerfile`

### 3️⃣ Variables de Entorno
Añade estas variables en la sección **Environment**:

```bash
DATABASE_URL=jdbc:postgresql://[TU_HOST]:5432/[TU_DB]?user=[USER]&password=[PASSWORD]&sslmode=require
JWT_SECRET=fWajnXHQR5hv1Jh8sAgtz5SutkEIcQVqxnlBYfNX4GPO31kbty1bDwIeUGMX5KeiMydIRng0W8wjVHaqKw8tbQ==
JWT_EXPIRATION=3600000
CORS_ALLOWED_ORIGINS=https://tu-frontend.vercel.app,http://localhost:3000
```

#### 🔐 Generar JWT_SECRET seguro (opcional):
Puedes generar uno nuevo ejecutando en terminal:
```bash
node -e "console.log(require('crypto').randomBytes(64).toString('base64'))"
```

### 4️⃣ Plan y Deploy
- **Instance Type**: Selecciona **Free** (para empezar)
- Haz clic en **"Create Web Service"**

### 5️⃣ Verificar Deployment
Una vez desplegado, tu API estará disponible en:
```
https://flexreact-backend.onrender.com
```

Prueba el health check:
```
https://flexreact-backend.onrender.com/api/health
```

## 🔄 Auto-Deploy
Render detecta automáticamente los cambios en tu rama `main` y redespliega.

## 📊 Monitoreo
- **Logs**: Dashboard → Tu servicio → Logs
- **Metrics**: Dashboard → Tu servicio → Metrics
- **Health**: Render hace health checks automáticos a `/api/health`

## ⚡ Optimizaciones

### Si usas Render PostgreSQL:
1. Crea una base de datos PostgreSQL en Render
2. En vez de copiar `DATABASE_URL`, usa el formato correcto para Spring Boot:
```bash
DATABASE_URL=jdbc:postgresql://[INTERNAL_HOST]:5432/[DB_NAME]?user=[USER]&password=[PASSWORD]
```

### Para producción:
- Cambia a un plan de pago para evitar cold starts
- Aumenta los recursos si es necesario
- Configura un dominio personalizado

## 🐛 Troubleshooting

### Error de conexión a DB:
- Verifica que `DATABASE_URL` tenga el formato correcto con `jdbc:postgresql://`
- Asegúrate de que Supabase/Render permita conexiones externas
- Verifica las credenciales

### CORS errors:
- Añade el dominio de tu frontend a `CORS_ALLOWED_ORIGINS`
- Formato: `https://tu-app.vercel.app,https://otro-dominio.com`

### Cold starts (plan Free):
- El servicio gratuito se "duerme" después de 15 min de inactividad
- Primera petición puede tardar 30-60 segundos
- Solución: Upgrade a plan de pago

## 🔗 URLs Importantes
- Dashboard: https://dashboard.render.com
- Docs: https://render.com/docs
- Status: https://status.render.com

## 📝 Notas
- Render hace build automático desde el Dockerfile
- El puerto se asigna automáticamente vía variable `PORT`
- Logs en tiempo real disponibles en el dashboard
- Health checks configurados en `/api/health`
