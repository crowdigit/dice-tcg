#version 100

attribute vec2 Vertex;

uniform mat4 M;
varying vec2 texCoord;

void main() {
    gl_Position = M * vec4(Vertex, 0.0, 1.0);
    texCoord.x = Vertex.x;
    texCoord.y = 1.0 - Vertex.y;
}