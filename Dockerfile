FROM maven:3.9.9

# Install dependencies
RUN apt-get update && apt-get install -y \
    wget gnupg2 curl unzip software-properties-common xvfb x11vnc fluxbox \
    && rm -rf /var/lib/apt/lists/*

# Add Chrome signing key and repo, install Chrome
RUN wget -q -O - https://dl.google.com/linux/linux_signing_key.pub | apt-key add - && \
    echo "deb [arch=amd64] http://dl.google.com/linux/chrome/deb/ stable main" > /etc/apt/sources.list.d/google-chrome.list && \
    apt-get update && apt-get install -y google-chrome-stable && \
    rm -rf /var/lib/apt/lists/*

# Install Firefox and GeckoDriver
RUN apt-get update && apt-get install -y firefox

# Create non-root user
RUN useradd -m selenium

# Set workdir
WORKDIR /home/selenium/app

# Copy project files
COPY . .

# Fix ownership so selenium user can read/write everything
RUN chown -R selenium:selenium /home/selenium/app

# Switch to non-root user
USER selenium

# Expose VNC port
EXPOSE 5900

# Set environment variables for display size
ENV DISPLAY=:99
ENV SCREEN_WIDTH=1366
ENV SCREEN_HEIGHT=768
ENV SCREEN_DEPTH=24

# Use ENTRYPOINT for the core script and allow parameters from CMD or runtime args
ENTRYPOINT ["bash", "-c", "\
    Xvfb :99 -screen 0 ${SCREEN_WIDTH}x${SCREEN_HEIGHT}x${SCREEN_DEPTH} & \
    fluxbox & \
    x11vnc -display :99 -nopw -forever -shared -rfbport 5900 -noxdamage & \
    sleep 5 && \
    mvn clean test \"$@\"", "--"]

# Default CMD argument if none provided
CMD ["-Dbrowser=Chrome"]
