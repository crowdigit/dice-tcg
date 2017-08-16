#version 100

attribute vec2 Vertex;

uniform mat4 M;

void main() {
    // gl_Position = M * vec4(Vertex, 0.0, 1.0);
    gl_Position = M * vec4(Vertex, 0.0, 1.0);
}