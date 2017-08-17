#version 100

uniform sampler2D tex;
varying mediump vec2 texCoord;

void main() {
    gl_FragColor = texture2D(tex, texCoord);
}