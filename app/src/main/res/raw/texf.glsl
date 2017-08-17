#version 100

uniform sampler2D tex;
varying mediump vec2 texCoord;
uniform mediump float fade;

void main() {
    gl_FragColor = fade * texture2D(tex, texCoord);
}