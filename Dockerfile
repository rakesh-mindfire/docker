FROM maven:3.9.9

# Install Chrome
RUN apt-get update && apt-get install -y wget gnupg2 \
    && wget -q -O - https://dl.google.com/linux/linux_signing_key.pub | apt-key add - \
    && echo "deb [arch=amd64] http://dl.google.com/linux/chrome/deb/ stable main" > /etc/apt/sources.list.d/google-chrome.list \
    && apt-get update && apt-get install -y google-chrome-stable \
    && rm -rf /var/lib/apt/lists/*

# Set working directory in container
WORKDIR /app

# ⬇️ This is the line that copies your entire project folder into the container
COPY . .

# Run Maven tests
CMD ["mvn", "clean", "test"]
