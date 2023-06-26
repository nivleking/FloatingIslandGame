package Engine;

import org.joml.Matrix4f;
import org.joml.Vector3f;
import org.joml.Vector4f;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL15.GL_STATIC_DRAW;
import static org.lwjgl.opengl.GL20.glEnableVertexAttribArray;
import static org.lwjgl.opengl.GL20.glVertexAttribPointer;
import static org.lwjgl.opengl.GL30.glBindVertexArray;
import static org.lwjgl.opengl.GL30.glGenVertexArrays;

public class Object extends ShaderProgram{

    List<Vector3f> vertices;
    int vao;
    int vbo;
    UniformsMap uniformsMap;
    Vector4f color;
    public Matrix4f model;
    List<Object> childObject;
    List<Float> centerPoint;
    boolean scene = true;
    float xR;
    float yR;
    float zR;
    float rotation;
    float jarakX, jarakY, jarakZ;

    boolean lampu = true;

    float r;

    Vector3f lightNight = new Vector3f(0.8f, 0.8f, 0.8f);
    Vector3f lightDay = new Vector3f(0.8f, 0.8f, 0.8f);
    boolean spotNyala = false;

    public void setScene(boolean scene) {
        this.scene = scene;
    }

    public void setSpot(boolean spotNyala) {
        this.spotNyala = spotNyala;
    }
    public boolean getSpot() {
        return (this.spotNyala);
    }

    public void setColor(Vector4f color) {
        this.color = color;
    }

    public List<Object> getChildObject() {
        return childObject;
    }

    public List<Float> getCenterPoint() {
        updateCenterPoint();
        return centerPoint;
    }
    public void setLampu(boolean lampu) {
        this.lampu = lampu;
    }
    public Object(List<ShaderModuleData> shaderModuleDataList
            , List<Vector3f> vertices
            , Vector4f color) {
        super(shaderModuleDataList);
        this.vertices = vertices;
//        setupVAOVBO();
        uniformsMap = new UniformsMap(getProgramId());
        uniformsMap.createUniform(
                "uni_color");
        uniformsMap.createUniform(
                "model");
        uniformsMap.createUniform(
                "projection");
        uniformsMap.createUniform(
                "view");
        uniformsMap.createUniform("dirLight.position");
        uniformsMap.createUniform("dirLight.direction");
        uniformsMap.createUniform("dirLight.ambient");
        uniformsMap.createUniform("dirLight.diffuse");
        uniformsMap.createUniform("dirLight.specular");
        for(int i = 0; i < 16; i++){
            uniformsMap.createUniform("pointLight["+i+"].position");
            uniformsMap.createUniform("pointLight["+i+"].ambient");
            uniformsMap.createUniform("pointLight["+i+"].diffuse");
            uniformsMap.createUniform("pointLight["+i+"].specular");
            uniformsMap.createUniform("pointLight["+i+"].constant");
            uniformsMap.createUniform("pointLight["+i+"].linear");
            uniformsMap.createUniform("pointLight["+i+"].quadratic");
        }
        uniformsMap.createUniform("spotLight.position");
        uniformsMap.createUniform("spotLight.direction");
        uniformsMap.createUniform("spotLight.ambient");
        uniformsMap.createUniform("spotLight.diffuse");
        uniformsMap.createUniform("spotLight.specular");
        uniformsMap.createUniform("spotLight.constant");
        uniformsMap.createUniform("spotLight.linear");
        uniformsMap.createUniform("spotLight.quadratic");
        uniformsMap.createUniform("spotLight.cutOff");
        uniformsMap.createUniform("spotLight.outerCutOff");
        uniformsMap.createUniform("viewPos");
        this.color = color;
        model = new Matrix4f().identity();
        childObject = new ArrayList<>();
        centerPoint = Arrays.asList(0f,0f,0f);
    }

    public Object(List<ShaderModuleData> shaderModuleDataList
            , List<Vector3f> vertices
            , Vector4f color, Vector3f lightDay, Vector3f lightNight) {
        super(shaderModuleDataList);
        this.vertices = vertices;
//        setupVAOVBO();
        uniformsMap = new UniformsMap(getProgramId());
        uniformsMap.createUniform(
                "uni_color");
        uniformsMap.createUniform(
                "model");
        uniformsMap.createUniform(
                "projection");
        uniformsMap.createUniform(
                "view");
        uniformsMap.createUniform("dirLight.position");
        uniformsMap.createUniform("dirLight.direction");
        uniformsMap.createUniform("dirLight.ambient");
        uniformsMap.createUniform("dirLight.diffuse");
        uniformsMap.createUniform("dirLight.specular");
        for(int i = 0; i < 16; i++){
            uniformsMap.createUniform("pointLight["+i+"].position");
            uniformsMap.createUniform("pointLight["+i+"].ambient");
            uniformsMap.createUniform("pointLight["+i+"].diffuse");
            uniformsMap.createUniform("pointLight["+i+"].specular");
            uniformsMap.createUniform("pointLight["+i+"].constant");
            uniformsMap.createUniform("pointLight["+i+"].linear");
            uniformsMap.createUniform("pointLight["+i+"].quadratic");
        }
        uniformsMap.createUniform("spotLight.position");
        uniformsMap.createUniform("spotLight.direction");
        uniformsMap.createUniform("spotLight.ambient");
        uniformsMap.createUniform("spotLight.diffuse");
        uniformsMap.createUniform("spotLight.specular");
        uniformsMap.createUniform("spotLight.constant");
        uniformsMap.createUniform("spotLight.linear");
        uniformsMap.createUniform("spotLight.quadratic");
        uniformsMap.createUniform("spotLight.cutOff");
        uniformsMap.createUniform("spotLight.outerCutOff");
        uniformsMap.createUniform("viewPos");
        this.color = color;
        model = new Matrix4f().identity();
        childObject = new ArrayList<>();
        centerPoint = Arrays.asList(0f,0f,0f);
        this.lightDay=lightDay;
        this.lightNight=lightNight;
    }

    public void setupVAOVBO(){
        //set vao
        vao = glGenVertexArrays();
        glBindVertexArray(vao);

        //set vbo
        vbo = glGenBuffers();
        glBindBuffer(GL_ARRAY_BUFFER, vbo);
        glBufferData(GL_ARRAY_BUFFER,
                Utils.listoFloat(vertices),
                GL_STATIC_DRAW);
    }

    public void drawSetup(Camera camera, Projection projection){
        bind();
        uniformsMap.setUniform(
                "uni_color", color);
        uniformsMap.setUniform(
                "model", model);
        uniformsMap.setUniform(
                "view", camera.getViewMatrix());
        uniformsMap.setUniform(
                "projection", projection.getProjMatrix());
        uniformsMap.setUniform("dirLight.position", new Vector3f(0f,5f,0f));
        uniformsMap.setUniform("dirLight.direction", new Vector3f(-0.2f, -1.0f, -0.3f));
        if(scene){
            uniformsMap.setUniform("dirLight.ambient", lightNight);
            uniformsMap.setUniform("dirLight.diffuse", new Vector3f(0.5f, 0.5f, 0.5f));
            uniformsMap.setUniform("dirLight.specular", new Vector3f(3f, 3f, 3f));
        } else {
            uniformsMap.setUniform("dirLight.ambient",lightDay);
            uniformsMap.setUniform("dirLight.diffuse", new Vector3f(0.9f, 1f, 0.1f));
            uniformsMap.setUniform("dirLight.specular", new Vector3f(3f, 3f, 3f));
        }


        Vector3f[] _pointLightPositions = {
                new Vector3f(1.05f, 1.05f, 0f),
                new Vector3f(2.4f, 1.05f, 1.1f),
                new Vector3f(-0.61f, 1.05f, 0f),
                new Vector3f(0.8f,1.f,1.3f),
                new Vector3f(-5.25f, 1.15f, 1f),
                new Vector3f(-5.25f, 1.15f, 1.75f),
                new Vector3f(-5.25f, 1.15f, 2.5f),
                new Vector3f(-5.25f, 1.15f, 3.1f),
                new Vector3f(0f, 0.8f, 4.8f),
                new Vector3f(0.8f, 0.8f, 4.8f),

                //kelvin
                new Vector3f(-4.9f, 1.07f, -2.3f),
                new Vector3f(-7f, 1.02f, -2.3f),
                new Vector3f(-4.2f, 1.02f, 3.9f),
                new Vector3f(-6.5f, 1.01f, 3.9f),

                //pat
                new Vector3f(8.4f, 1f, -1.65f),
                new Vector3f(14.4f, 0.6f, 8.2f),
        };

        for(int i = 0; i < _pointLightPositions.length; i++){
            if(i >= 14) {
                uniformsMap.setUniform("pointLight["+i+"].position", _pointLightPositions[i]);
                uniformsMap.setUniform("pointLight["+i+"].constant", 1f);
                uniformsMap.setUniform("pointLight["+i+"].linear", 5f);
                uniformsMap.setUniform("pointLight["+i+"].quadratic", 3f);
                if (scene) {
                    uniformsMap.setUniform("pointLight["+i+"].ambient", new Vector3f(3.5f, 1.5f, 0.1f));
                    uniformsMap.setUniform("pointLight["+i+"].diffuse", new Vector3f(1.5f, 0.1f, 0.1f));
                    uniformsMap.setUniform("pointLight["+i+"].specular", new Vector3f(0.7f, 0.7f, 0.7f));
                } else {
                    uniformsMap.setUniform("pointLight["+i+"].ambient", new Vector3f(0f, 0f, 0f));
                    uniformsMap.setUniform("pointLight["+i+"].diffuse", new Vector3f(0f, 0f, 0f));
                    uniformsMap.setUniform("pointLight["+i+"].specular", new Vector3f(1f, 1f, 1f));
                }
            } else if(i >= 10) {
                uniformsMap.setUniform("pointLight["+i+"].position", _pointLightPositions[i]);
//            if(scene){
//                uniformsMap.setUniform("pointLight["+i+"].ambient", new Vector3f(0.1f, 0.1f, 0.1f));
//            } else {
//                uniformsMap.setUniform("pointLight["+i+"].ambient", new Vector3f(0.4f, 0.4f, 0.4f));
//            }
//                uniformsMap.setUniform("pointLight["+i+"].direction", new Vector3f(-0.2f, -1.0f, -0.3f));
                uniformsMap.setUniform("pointLight["+i+"].constant", 1f);
                uniformsMap.setUniform("pointLight["+i+"].linear", 5f);
                uniformsMap.setUniform("pointLight["+i+"].quadratic", 3f);
                if (scene) {
                    uniformsMap.setUniform("pointLight["+i+"].ambient", new Vector3f(1.5f, 0.1f, 0.1f));
                    uniformsMap.setUniform("pointLight["+i+"].diffuse", new Vector3f(1.5f, 0.1f, 0.1f));
                    uniformsMap.setUniform("pointLight["+i+"].specular", new Vector3f(0.7f, 0.7f, 0.7f));
                } else {
                    uniformsMap.setUniform("pointLight["+i+"].ambient", new Vector3f(0f, 0f, 0f));
                    uniformsMap.setUniform("pointLight["+i+"].diffuse", new Vector3f(0f, 0f, 0f));
                    uniformsMap.setUniform("pointLight["+i+"].specular", new Vector3f(1f, 1f, 1f));
                }
            }
            else {
                uniformsMap.setUniform("pointLight["+i+"].position", _pointLightPositions[i]);
//            if(scene){
//                uniformsMap.setUniform("pointLight["+i+"].ambient", new Vector3f(0.1f, 0.1f, 0.1f));
//            } else {
//                uniformsMap.setUniform("pointLight["+i+"].ambient", new Vector3f(0.4f, 0.4f, 0.4f));
//            }

                uniformsMap.setUniform("pointLight["+i+"].constant", 1.0f);
                uniformsMap.setUniform("pointLight["+i+"].linear", 3f);
                uniformsMap.setUniform("pointLight["+i+"].quadratic", 4f);
                if (scene) {
                    uniformsMap.setUniform("pointLight["+i+"].ambient", new Vector3f(0.8f, 0.8f, 0.8f));
                    uniformsMap.setUniform("pointLight["+i+"].diffuse", new Vector3f(0.4f, 0.4f, 0.4f));
                    uniformsMap.setUniform("pointLight["+i+"].specular", new Vector3f(0.5f, 0.5f, 0.5f));
                } else {
                    uniformsMap.setUniform("pointLight["+i+"].ambient", new Vector3f(0f, 0f, 0f));
                    uniformsMap.setUniform("pointLight["+i+"].diffuse", new Vector3f(0f, 0f, 0f));
                    uniformsMap.setUniform("pointLight["+i+"].specular", new Vector3f(1f, 1f, 1f));
                }
            }
        }

        // spotLight
        if (spotNyala) {
            uniformsMap.setUniform("spotLight.position", camera.getPosition());
            uniformsMap.setUniform("spotLight.direction", new Vector3f(
                    camera.getViewMatrix().get(2, 0),
                    camera.getViewMatrix().get(2, 1),
                    -camera.getViewMatrix().get(2, 2)
            ));
            uniformsMap.setUniform("spotLight.ambient", new Vector3f(.05f, .05f, .05f));
            uniformsMap.setUniform("spotLight.diffuse", new Vector3f(1.0f, 1.0f, 1.0f));
            uniformsMap.setUniform("spotLight.specular", new Vector3f(.75f, .75f, .75f));
            uniformsMap.setUniform("spotLight.constant", 1.0f);
            uniformsMap.setUniform("spotLight.linear", 0.09f);
            uniformsMap.setUniform("spotLight.quadratic", 0.032f);
            uniformsMap.setUniform("spotLight.cutOff", (float) Math.cos(Math.toRadians(12f)));
            uniformsMap.setUniform("spotLight.outerCutOff", (float) Math.cos(Math.toRadians(12.5f)));
        } else {
            uniformsMap.setUniform("spotLight.position", camera.getPosition());
            uniformsMap.setUniform("spotLight.direction", new Vector3f(
                    camera.getViewMatrix().get(2,0),
                    camera.getViewMatrix().get(2,1),
                    -camera.getViewMatrix().get(2,2)
            ));
            uniformsMap.setUniform("spotLight.ambient", new Vector3f(0.0f, 0.0f, 0.0f));
            uniformsMap.setUniform("spotLight.diffuse", new Vector3f(0.0f, 0.0f, 0.0f));
            uniformsMap.setUniform("spotLight.specular", new Vector3f(0.0f, 0.0f, 0.0f));
            uniformsMap.setUniform("spotLight.constant", 1.0f);
            uniformsMap.setUniform("spotLight.linear", 0.00f);
            uniformsMap.setUniform("spotLight.quadratic", 0.00f);
            uniformsMap.setUniform("spotLight.cutOff", (float) Math.cos(Math.toRadians(20f)));
            uniformsMap.setUniform("spotLight.outerCutOff", (float) Math.cos(Math.toRadians(21f)));

        }
        uniformsMap.setUniform("viewPos", camera.getPosition());

        // Bind VBO
        glEnableVertexAttribArray(0);
        glBindBuffer(GL_ARRAY_BUFFER, vbo);
        glVertexAttribPointer(0, 3,
                GL_FLOAT,
                false,
                0, 0);

    }

    public void draw(Camera camera, Projection projection){
        drawSetup(camera, projection);
        // Draw the vertices
        //optional
        glLineWidth(10); //ketebalan garis
        glPointSize(10); //besar kecil vertex
        //wajib
        //GL_LINES
        //GL_LINE_STRIP
        //GL_LINE_LOOP
        //GL_TRIANGLES
        //GL_TRIANGLE_FAN
        //GL_POINT
        glDrawArrays(GL_TRIANGLES,
                0,
                vertices.size());
        for(Object child:childObject){
            child.draw(camera,projection);
        }
    }

    public void translateObject(Float offsetX,Float offsetY,Float offsetZ){
        model = new Matrix4f().translate(offsetX,offsetY,offsetZ).mul(new Matrix4f(model));
        // update center point tak apus buat rotasi di tempat
        for(Object child:childObject){
            child.translateObject(offsetX,offsetY,offsetZ);
        }
    }

    public void rotateObject(Float degree, Float x,Float y,Float z){
        model = new Matrix4f().rotate(degree,x,y,z).mul(new Matrix4f(model));
        // update center point tak apus buat rotasi di tempat
        for(Object child:childObject){
            child.rotateObject(degree,x,y,z);
        }
        this.xR = (float) (r * Math.cos(degree) + xR);
        this.yR = (float) (r * Math.sin(degree) + yR);
    }
    public void translateObjectSpesial(Float offsetX,Float offsetY,Float offsetZ){
        model = new Matrix4f().translate(offsetX, offsetY, offsetZ).mul(new Matrix4f(model));
        this.xR+=offsetX;
        this.yR+=offsetY;
        this.zR+=offsetZ;
        for(Object child:childObject) {
            child.translateObject(offsetX, offsetY, offsetZ);
        }
        //System.out.println(offsetX+" "+offsetY+" "+offsetZ);
        //System.out.println(rotation+"AWHJHWFA");
        jarakX+=offsetX*Math.cos(Math.toRadians(rotation));
        jarakY+=offsetY;
        //jarakY+=offsetX*Math.sin(Math.toRadians(rotation));
        jarakZ+=offsetX*Math.sin(Math.toRadians(rotation));
    }

    public void rotateObjectSpesial(Float degree, Float x,Float y,Float z){
        model = new Matrix4f().rotate(degree,x,y,z).mul(new Matrix4f(model));
        // update center point tak apus buat rotasi di tempat
        for(Object child:childObject){
            child.rotateObject(degree,x,y,z);
        }
        this.xR = (float) (r * Math.cos(degree) + xR);
        this.yR = (float) (r * Math.sin(degree) + yR);
        rotation+=Math.toDegrees(degree);
        rotation=(360+rotation)%360f;
    }
    public void updateCenterPoint(){
        Vector3f destTemp = new Vector3f();
        model.transformPosition(0.0f,0.0f,0.0f,destTemp);
        centerPoint.set(0,destTemp.x);
        centerPoint.set(1,destTemp.y);
        centerPoint.set(2,destTemp.z);
    }
    public void scaleObject(Float scaleX,Float scaleY,Float scaleZ){
        model = new Matrix4f().scale(scaleX,scaleY,scaleZ).mul(new Matrix4f(model));
        for(Object child:childObject){
            child.translateObject(scaleX,scaleY,scaleZ);
        }
    }
    public float getJarakX() {
        return jarakX;
    }

    public float getJarakY() {
        return jarakY;
    }

    public float getJarakZ() {
        return jarakZ;
    }

    public float getRotation() {
        return rotation;
    }

    public void setAmbient (Vector3f lightDay, Vector3f lightNight) {
        this.lightDay=lightDay;
        this.lightNight=lightNight;
    }
}
