#version 100

attribute vec3 Vertex;

void main() {
    gl_Position = vec4(Vertex, 1.0);
}