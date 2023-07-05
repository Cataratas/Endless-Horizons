#version 150
#moj_import <projection.glsl>

uniform mat4 ProjMat;
uniform mat4 ModelViewMat;

in vec3 Position;
out vec4 texProj0;

void main() {
    gl_Position = ProjMat * ModelViewMat * vec4(Position, 1.0);
    texProj0 = projection_from_position(gl_Position);
}
