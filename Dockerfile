# Etapa 1: Usar uma imagem base com OpenJDK (Debian)
FROM openjdk:21-slim as build

# Instalar Maven
RUN apt-get update && apt-get install -y maven

# Definir o diretório de trabalho
WORKDIR /app

# Copiar o código-fonte para o container
COPY . /app

# Rodar o comando Maven para construir o JAR
RUN mvn clean package -DskipTests

# Etapa 2: Usar uma imagem base apenas com o OpenJDK
FROM openjdk:21-slim

# Definir o diretório de trabalho
WORKDIR /app

# Copiar o arquivo JAR gerado da etapa anterior
COPY --from=build /app/target/investments-0.0.1-SNAPSHOT.jar app.jar

# Expor a porta
EXPOSE 8080

# Comando para rodar a aplicação
ENTRYPOINT ["java", "-jar", "app.jar"]
