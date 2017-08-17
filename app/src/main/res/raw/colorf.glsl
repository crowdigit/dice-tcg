#version 100

uniform mediump vec3 color;
uniform mediump float fade;

void main() {
    gl_FragColor = fade * vec4(color, 1.0);
}