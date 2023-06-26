import Engine.*;
import Engine.Object;
import org.joml.*;
import org.lwjgl.opengl.GL;

import java.io.IOException;
import java.lang.Math;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.glClearColor;
import static org.lwjgl.opengl.GL20.GL_FRAGMENT_SHADER;
import static org.lwjgl.opengl.GL20.GL_VERTEX_SHADER;
import static org.lwjgl.opengl.GL20.glDisableVertexAttribArray;
import static org.lwjgl.opengl.GL30.*;

public class Main {
    private Window window = new Window(1080, 720, "Hello World");
    private MouseInput mouseInput;
    ArrayList<Object> objectMainIsland = new ArrayList<>();
    ArrayList<Object> objectSecondIsland = new ArrayList<>();
    ArrayList<Object> objectThirdIsland = new ArrayList<>();
    ArrayList<Object> objectMisc = new ArrayList<>();

    ArrayList<Object> objectTerbang = new ArrayList<>();
    ArrayList<Object> objectTerbang2= new ArrayList<>();

    ArrayList<Object> objectTower= new ArrayList<>();

    // acto
    ArrayList<Object> objectLampu = new ArrayList<>();
    ArrayList<Object> objectGolem = new ArrayList<>();

    //pat

    ArrayList<Object> objectPlane= new ArrayList<>();
    ArrayList<Object> objectHeli= new ArrayList<>();
    ArrayList<Object> objectBuilding= new ArrayList<>();
    ArrayList<Object> objectFiretruck= new ArrayList<>();
    ArrayList<Object> objectMonster= new ArrayList<>();
    ArrayList<Object> objectBus= new ArrayList<>();
    ArrayList<Object> objectPolice= new ArrayList<>();
    ArrayList<Object> objectCoupe= new ArrayList<>();
    ArrayList<Object> objectIslandPat= new ArrayList<>();
    ArrayList<Object> objectNewIslandPat= new ArrayList<>();
    ArrayList<ArrayList<Object>> objectCars = new ArrayList<>();
    int[] rotate = {0,0,0,0,0,0,0, 0};
    int noBrp = 1;
    int personBrp = 3;
    int intervalCinematic = 0;
    Vector3f positionCam=new Vector3f(-6.5f, 5.046f, 9f);
    Vector2f rotationCam=new Vector2f((float)Math.toRadians(30f),(float)Math.toRadians(30f));
    boolean camSet = false;
    boolean cinematic = false;
    Vector4f warnaBlue = new Vector4f(28/255f,28/255f,251/255f,1.0f);
    Vector4f warnaBlue2 = new Vector4f(20/255f,20/255f,156/255f,1.0f);
    Vector4f warnaRed = new Vector4f(237/255f,0/255f,20/255f,1.0f);
    Vector4f warnaGreen = new Vector4f(56/255f,200/255f,54/255f,1.0f);
    Vector4f warnaGreen2 = new Vector4f(28/255f,170/255f,38/255f,1.0f);
    Vector4f warnaYellow = new Vector4f(237/255f,217/255f,24/255f,1.0f);
    Vector4f warnaBrown = new Vector4f(60/255f,30/255f,10/255f,1.0f);
    Vector4f warnaBrown2 = new Vector4f(44/255f,25/255f,10/255f,1.0f);
    Vector4f warnaBlack = new Vector4f(30/255f,30/255f,30/255f,1.0f);
    Vector4f warnaGrey = new Vector4f(100/255f,100/255f,100/255f,1.0f);
    Vector4f warnaWhite = new Vector4f(243/255f,247/255f,247/255f,1.0f);
    Vector4f warnaScreen = new Vector4f(172/255f,200/255f,215/255f,1.0f);
    //end pat
    Camera camera = new Camera();
    Projection projection = new Projection(window.getWidth(), window.getHeight());
    float angle = 0f;
    List<Float> temp;
    boolean malam = true;
    boolean delay2 = false;
    int delayCounter2 = 0;

    public void run() throws IOException {
        init();
        loop();

        // Terminate GLFW and free the error callback
        glfwTerminate();
        glfwSetErrorCallback(null).free();
    }

    public void init() throws IOException {
        window.init();
        GL.createCapabilities();
        camera.setPosition(-6.5f, 5.046f, 9f);
        camera.addRotation((float)Math.toRadians(30f),
                (float)Math.toRadians(30f));

        /*camera.setPosition(-3f, 0.8f, 0);
        camera.setRotation(0f,(float) org.joml.Math.toRadians(90f));
        glClearColor(0.0f, 0.64453125f, 1.0f, 1.0f);*/
        mouseInput = window.getMouseInput();

        // 1 - MAIN ISLAND
        objectMainIsland.add(new Model(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(0.3f,0.6f,0.3f,1.0f),
                "resources/model/1mainIsland/1mainIsland-upper.obj"
        ));
        objectMainIsland.get(0).scaleObject(0.05f,0.05f,0.05f);

        objectMainIsland.add(new Model(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(0.3f,0.2f,0.0f,1.0f),
                "resources/model/1mainIsland/1mainIsland-bottom.obj"
        ));
        objectMainIsland.get(1).scaleObject(0.05f,0.05f,0.05f);

        objectMainIsland.add(new Model(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(0.387f,0.387f,0.387f,1.0f),
                "resources/model/1mainIsland/1mainIsland-pavement.obj"
        ));
        objectMainIsland.get(2).scaleObject(0.05f,0.05f,0.05f);

        //bangunan" kecil
        objectMainIsland.add(new Model(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(0.3f,0.0f,0.2f,1.0f),
                "resources/model/1mainIsland/1mainIsland-city1.obj"
        ));
        objectMainIsland.get(3).scaleObject(0.05f,0.05f,0.05f);

        //menara - 1
        objectMainIsland.add(new Model(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(0.5f,0.4f,0.0f,1.0f),
                "resources/model/1mainIsland/1mainIsland-city2.obj"
        ));
        objectMainIsland.get(4).scaleObject(0.05f,0.05f,0.05f);

        //menara - 2
        objectMainIsland.add(new Model(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(1f,0.2f,0.0f,1.0f),
                "resources/model/1mainIsland/1mainIsland-city3.obj"
        ));
        objectMainIsland.get(5).scaleObject(0.05f,0.05f,0.05f);

        //menara - 3
        objectMainIsland.add(new Model(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(1f,0.4f,0.4f,1.0f),
                "resources/model/1mainIsland/1mainIsland-city4.obj"
        ));
        objectMainIsland.get(6).scaleObject(0.05f,0.05f,0.05f);

        //menara - 4
        objectMainIsland.add(new Model(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(0.5f,1f,0.0f,1.0f),
                "resources/model/1mainIsland/1mainIsland-city5.obj"
        ));
        objectMainIsland.get(7).scaleObject(0.05f,0.05f,0.05f);

        //kota sedang - 1
        objectMainIsland.add(new Model(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(0.5f,0.5f,0.0f,1.0f),
                "resources/model/1mainIsland/1mainIsland-city6.obj"
        ));
        objectMainIsland.get(8).scaleObject(0.05f,0.05f,0.05f);
//
        //kota sedang - 2
        objectMainIsland.add(new Model(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(0f,0f,1f,1.0f),
                "resources/model/1mainIsland/1mainIsland-city7.obj"
        ));
        objectMainIsland.get(9).scaleObject(0.05f,0.05f,0.05f);

        //kota sedang - 3
        objectMainIsland.add(new Model(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(1f,0f,0f,1.0f),
                "resources/model/1mainIsland/1mainIsland-city8.obj"
        ));
        objectMainIsland.get(10).scaleObject(0.05f,0.05f,0.05f);

        // 2 - SECOND ISLAND
        objectSecondIsland.add(new Model(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(0.3f,0.2f,0.0f,1.0f),
                "resources/model/2secondIsland/2secondIsland-bottom.obj"
        ));
        objectSecondIsland.get(0).scaleObject(0.05f,0.05f,0.05f);

        objectSecondIsland.add(new Model(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(0.3f,0.6f,0.3f,1.0f),
                "resources/model/2secondIsland/2secondIsland-upper.obj"
        ));
        objectSecondIsland.get(1).scaleObject(0.05f,0.05f,0.05f);

        objectSecondIsland.add(new Model(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(0.2f,0.3f,1f,1.0f),
                "resources/model/2secondIsland/2secondIsland-tower.obj"
        ));
        objectSecondIsland.get(2).scaleObject(0.05f,0.05f,0.05f);

        // 3 - THIRD ISLAND
        objectThirdIsland.add(new Model(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(0.3f,0.6f, 0.3f,1.0f),
                "resources/model/3thirdIsland/3thirdIsland-upper.obj"
        ));
        objectThirdIsland.get(0).scaleObject(0.05f,0.05f,0.05f);

        objectThirdIsland.add(new Model(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(0.3f,0.2f,0.0f,1.0f),
                "resources/model/3thirdIsland/3thirdIsland-bottom.obj"
        ));
        objectThirdIsland.get(1).scaleObject(0.05f,0.05f,0.05f);

        objectThirdIsland.add(new Model(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(0.1f,0.1f,0.1f,1.0f),
                "resources/model/3thirdIsland/3thirdIsland-highway.obj"
        ));
        objectThirdIsland.get(2).scaleObject(0.05f,0.05f,0.05f);

        objectThirdIsland.add(new Model(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(0.9f,0.9f,0.9f,1.0f),
                "resources/model/3thirdIsland/3thirdIsland-blimp.obj"
        ));
        objectThirdIsland.get(3).scaleObject(0.05f,0.05f,0.05f);

        //pesawat - 1
        objectThirdIsland.add(new Model(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(0.6f,0.0f,0.0f,1.0f),
                "resources/model/3thirdIsland/3thirdIsland-plane1.obj"
        ));
        objectThirdIsland.get(4).scaleObject(0.05f,0.05f,0.05f);

        //pesawat - 2
        objectThirdIsland.add(new Model(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(0.2f,0.2f,0.7f,1.0f),
                "resources/model/3thirdIsland/3thirdIsland-plane2.obj"
        ));
        objectThirdIsland.get(5).scaleObject(0.05f,0.05f,0.05f);

        //pesawat - 3
        objectThirdIsland.add(new Model(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(0.2f,0.2f,0.7f,1.0f),
                "resources/model/3thirdIsland/3thirdIsland-plane3.obj"
        ));
        objectThirdIsland.get(6).scaleObject(0.05f,0.05f,0.05f);

        //pesawat - 4
        objectThirdIsland.add(new Model(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(0.2f,0.2f,0.7f,1.0f),
                "resources/model/3thirdIsland/3thirdIsland-plane4.obj"
        ));
        objectThirdIsland.get(7).scaleObject(0.05f,0.05f,0.05f);

        //radio tower - 1
        objectThirdIsland.add(new Model(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(0.9f,0.9f,0.9f,1.0f),
                "resources/model/3thirdIsland/3thirdIsland-radioTower1.obj"
        ));
        objectThirdIsland.get(8).scaleObject(0.05f,0.05f,0.05f);

        objectThirdIsland.add(new Model(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(0.1f,0.1f,0.1f,1.0f),
                "resources/model/3thirdIsland/3thirdIsland-radioTower2.obj"
        ));
        objectThirdIsland.get(9).scaleObject(0.05f,0.05f,0.05f);

        objectThirdIsland.add(new Model(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(0.3f,0.4f,0.5f,1.0f),
                "resources/model/3thirdIsland/3thirdIsland-radioTower3.obj"
        ));
        objectThirdIsland.get(10).scaleObject(0.05f,0.05f,0.05f);

        objectThirdIsland.add(new Model(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(0.7f,0.7f,0.7f,1.0f),
                "resources/model/3thirdIsland/3thirdIsland-radioTower4.obj"
        ));
        objectThirdIsland.get(11).scaleObject(0.05f,0.05f,0.05f);

        objectThirdIsland.add(new Model(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(0.0f,0.0f,1f,1.0f),
                "resources/model/3thirdIsland/3thirdIsland-radioTower5.obj"
        ));
        objectThirdIsland.get(12).scaleObject(0.05f,0.05f,0.05f);

        objectThirdIsland.add(new Model(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(0.3f,0.4f,0.3f,1.0f),
                "resources/model/3thirdIsland/3thirdIsland-hangar.obj"
        ));
        objectThirdIsland.get(13).scaleObject(0.05f,0.05f,0.05f);

        // 4 - MISC
        objectMisc.add(new Model(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(0.3f,0.6f,0.3f,1.0f),
                "resources/model/4misc/4misc-upper.obj"
        ));
        objectMisc.get(0).scaleObject(0.05f,0.05f,0.05f);

        objectMisc.add(new Model(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(0.3f,0.2f,0.0f,1.0f),
                "resources/model/4misc/4misc-bottom.obj"
        ));
        objectMisc.get(1).scaleObject(0.05f,0.05f,0.05f);

//        objectMisc.add(new Model(
//                Arrays.asList(
//                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER),
//                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER)
//                ),
//                new ArrayList<>(),
//                new Vector4f(0.9f,0.9f,0.9f,1.0f),
//                "resources/model/4misc/4misc-blimp.obj"
//        ));
//        objectMisc.get(2).scaleObject(0.05f,0.05f,0.05f);

        objectMisc.add(new Model(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(0.4f,0.4f,0.4f,1.0f),
                "resources/model/4misc/4misc-iron.obj"
        ));
        objectMisc.get(2).scaleObject(0.05f,0.05f,0.05f);

        objectMisc.add(new Model(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(0.2f,0.1f,0.0f,1.0f),
                "resources/model/4misc/4misc-wood.obj"
        ));
        objectMisc.get(3).scaleObject(0.05f,0.05f,0.05f);

        objectMisc.add(new Model(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(0.5f,0.2f,0.0f,1.0f),
                "resources/model/4misc/4misc-stairs.obj"
        ));
        objectMisc.get(4).scaleObject(0.05f,0.05f,0.05f);

        objectMisc.add(new Model(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(0.8f,0.1f,0.1f,1.0f),
                "resources/model/sun.obj"
        ));
        objectMisc.get(5).scaleObject(0.001f,0.001f,0.001f);
        objectMisc.get(5).translateObjectSpesial(-4.9f, 1.07f, -2.3f);

        objectMisc.add(new Model(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(0.8f,0.1f,0.1f,1.0f),
                "resources/model/sun.obj"
        ));
        objectMisc.get(6).scaleObject(0.001f,0.001f,0.001f);
        objectMisc.get(6).translateObjectSpesial(-4.2f, 1.02f, 3.9f);

        objectMisc.add(new Model(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(0.8f,0.1f,0.1f,1.0f),
                "resources/model/sun.obj"
        ));
        objectMisc.get(7).scaleObject(0.001f,0.001f,0.001f);
        objectMisc.get(7).translateObjectSpesial(-7f, 1.02f, -2.3f);

        objectMisc.add(new Model(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(0.8f,0.1f,0.1f,1.0f),
                "resources/model/sun.obj"
        ));
        objectMisc.get(8).scaleObject(0.001f,0.001f,0.001f);
        objectMisc.get(8).translateObjectSpesial(-6.5f, 1.01f, 3.9f);

        objectTerbang.add(new Model(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(0.9f,0.9f,0.9f,1.0f),
                "resources/model/4misc/4misc-blimp.obj"
        ));
        objectTerbang.get(0).scaleObject(0.05f,0.05f,0.05f);

        objectTerbang.add(new Model(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(0.4f,0.2f,0.1f,1.0f),
                "resources/model/4misc/4misc-blimp.obj"
        ));
        objectTerbang.get(1).scaleObject(0.05f,0.05f,0.05f);
        objectTerbang.get(1).translateObjectSpesial(-10f,0.84f,-5f);

        objectTower.add(new Model(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(0.8f,0.8f,0.8f,1.0f),
                "resources/model/4misc/4tower-door.obj"
        ));

        objectTower.add(new Model(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(0.3f,0.4f,0.8f,1.0f),
                "resources/model/4misc/4tower-wallBottom.obj"
        ));

        objectTower.add(new Model(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(0.3f,0.4f,0.8f,1.0f),
                "resources/model/4misc/4tower-wallMiddle.obj"
        ));

        objectTower.add(new Model(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(0f,0f,0f,1.0f),
                "resources/model/4misc/4tower-upperDoor.obj"
        ));

        objectTower.add(new Model(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(0.3f,0.8f,0.9f,1.0f),
                "resources/model/4misc/4tower-window.obj"
        ));


        objectTower.add(new Model(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(0.3f,0.3f,0.3f,1.0f),
                "resources/model/4misc/4tower-iron.obj"
        ));

        objectTower.add(new Model(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(.0f,.0f,.0f,1.0f),
                "resources/model/4misc/4tower-penyangga.obj"
        ));

        objectTower.add(new Model(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(1f,1f,1f,1.0f),
                "resources/model/4misc/4tower-penyangga2.obj"
        ));

        objectTower.add(new Model(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(0.1f,0.1f,0.f,1.0f),
                "resources/model/4misc/4tower-jarumJam.obj"
        ));

        objectTower.add(new Model(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(0.3f,0.4f,0.8f,1.0f),
                "resources/model/4misc/4tower-dindingJam1.obj"
        ));


        objectTower.add(new Model(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(0.3f,0.4f,0.8f,1.0f),
                "resources/model/4misc/4tower-dindingJam2.obj"
        ));


        objectTower.add(new Model(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(0.2f,0.2f,0.2f,1.0f),
                "resources/model/4misc/4tower-roof.obj"
        ));

        objectTower.add(new Model(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(0f,0f,0f,1.0f),
                "resources/model/4misc/4tower-snowflakes.obj"
        ));

        for (int i = 0; i < objectTower.size(); i++) {
            objectTower.get(i).rotateObject((float)Math.toRadians(0f), 0f,1f,0f);
            objectTower.get(i).scaleObject(1f,1f,1f);
            objectTower.get(i).translateObjectSpesial(0.4f, 0.52f, 5.14f);
        }


        objectTerbang2.add(new Model(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(0.1f,0.3f,0.8f,1.0f),
                "resources/model/plane/plane_yellow.obj"
        ));
        objectTerbang2.add(new Model(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(0.1f,0.3f,0.4f,1.0f),
                "resources/model/plane/plane_red.obj"
        ));
        objectTerbang2.add(new Model(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER)
                ),
                new ArrayList<>(),
                warnaBlack,
                "resources/model/plane/plane_black.obj"
        ));

        objectTerbang2.add(new Model(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER)
                ),
                new ArrayList<>(),
                warnaScreen,
                "resources/model/plane/plane_screen.obj"
        ));
        objectTerbang2.add(new Model(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER)
                ),
                new ArrayList<>(),
                warnaBlack,
                "resources/model/plane/plane_fan.obj"
        ));
        for (int i = 0; i < objectTerbang2.size(); i++) {
            objectTerbang2.get(i).rotateObject((float)Math.toRadians(90f), 0f,1f,0f);
            objectTerbang2.get(i).scaleObject(0.1f,0.1f,0.1f);
            objectTerbang2.get(i).translateObjectSpesial(3f, 0.95f, 0f);
//                objectTerbang2.get(i).translateObjectSpesial(-6.04f, 0.95f, -3.27f);
        }


        objectLampu.add(new Model(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER)
                ),
                new ArrayList<>(),
                warnaRed,
                "resources/model/streetLamp.obj"
        ));
        objectLampu.add(new Model(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER)
                ),
                new ArrayList<>(),
                warnaRed,
                "resources/model/streetLamp.obj"
        ));
        objectLampu.add(new Model(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER)
                ),
                new ArrayList<>(),
                warnaRed,
                "resources/model/streetLamp.obj"
        ));
        objectLampu.add(new Model(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER)
                ),
                new ArrayList<>(),
                warnaRed,
                "resources/model/streetLamp.obj"
        ));

        objectLampu.add(new Model(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER)
                ),
                new ArrayList<>(),
                warnaRed,
                "resources/model/streetLamp.obj"
        ));
        objectLampu.add(new Model(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER)
                ),
                new ArrayList<>(),
                warnaRed,
                "resources/model/streetLamp.obj"
        ));objectLampu.add(new Model(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER)
                ),
                new ArrayList<>(),
                warnaRed,
                "resources/model/streetLamp.obj"
        ));objectLampu.add(new Model(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER)
                ),
                new ArrayList<>(),
                warnaRed,
                "resources/model/streetLamp.obj"
        ));

        objectLampu.add(new Model(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER)
                ),
                new ArrayList<>(),
                warnaRed,
                "resources/model/streetLamp.obj"
        ));

        objectLampu.add(new Model(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER)
                ),
                new ArrayList<>(),
                warnaRed,
                "resources/model/streetLamp.obj"
        ));



        objectLampu.add(new Model(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(.5f,.5f,.5f,0),
                "resources/model/sun.obj"
        ));

        objectLampu.get(0).scaleObject(0.003f,0.003f,0.003f);
        objectLampu.get(0).translateObject(0.93f,0.93f,0f);

        objectLampu.get(1).scaleObject(0.003f,0.003f,0.003f);
        objectLampu.get(1).translateObject(2.2f,0.93f,1f);

        objectLampu.get(2).scaleObject(0.003f,0.003f,0.003f);
        objectLampu.get(2).translateObject(-0.55f,0.93f,0f);

        objectLampu.get(3).scaleObject(0.003f,0.003f,0.003f);
        objectLampu.get(3).translateObject(0.7f,0.93f,1.25f);

        objectLampu.get(4).scaleObject(0.003f,0.003f,0.003f);
        objectLampu.get(4).rotateObject((float) Math.toRadians(90f),0f,1f,0.0f);
        objectLampu.get(4).translateObject(-5.25f,1.1f,1f);

        objectLampu.get(5).scaleObject(0.003f,0.003f,0.003f);
        objectLampu.get(5).rotateObject((float) Math.toRadians(90f),0f,1f,0.0f);
        objectLampu.get(5).translateObject(-5.2f,1.1f,1.75f);

        objectLampu.get(6).scaleObject(0.003f,0.003f,0.003f);
        objectLampu.get(6).rotateObject((float) Math.toRadians(90f),0f,1f,0.0f);
        objectLampu.get(6).translateObject(-5.15f,1.1f,2.45f);

        objectLampu.get(7).scaleObject(0.003f,0.003f,0.003f);
        objectLampu.get(7).rotateObject((float) Math.toRadians(90f),0f,1f,0.0f);
        objectLampu.get(7).translateObject(-5.1f,1.1f,3.1f);

        objectLampu.get(8).scaleObject(0.003f,0.003f,0.003f);
//        objectLampu.get(8).rotateObject((float) Math.toRadians(90f),0f,1f,0.0f);
        objectLampu.get(8).translateObject(-0.1f,0.74f,4.8f);

        objectLampu.get(9).scaleObject(0.003f,0.003f,0.003f);
        objectLampu.get(9).rotateObject((float) Math.toRadians(180f),0f,1f,0.0f);
        objectLampu.get(9).translateObject(0.95f,0.74f,4.8f);


        objectLampu.get(10).scaleObject(0.005f,0.005f,0.005f);
        objectLampu.get(10).translateObject(0f,5f,0f);

        objectGolem.add(new Model(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(.55f,.83f,1f,0),
                "resources/model/golemBody.obj"
        ));
        objectGolem.add(new Model(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(.55f,1f,.98f,0),
                "resources/model/golemHand.obj"
        ));
        objectGolem.get(0).getChildObject().add(new Model(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(0f,0f,0f,0),
                "resources/model/golemEye.obj"
        ));
        objectGolem.get(0).scaleObject(0.1f,0.1f,0.1f);
        objectGolem.get(0).rotateObject((float) Math.toRadians(180f),0f,1f,0f);
        objectGolem.get(0).translateObject(0.47f,4.8f,5.1f);
        objectGolem.get(1).scaleObject(0.1f,0.1f,0.1f);
        objectGolem.get(1).rotateObject((float) Math.toRadians(180f),0f,1f,0f);
        objectGolem.get(1).rotateObject((float) Math.toRadians(180f),1f,0f,0f);
        objectGolem.get(1).translateObject(0.47f,6.4f,5.1f);
        objectGolem.get(0).getChildObject().get(0).scaleObject(0.1f,.1f,0.1f);
        objectGolem.get(0).getChildObject().get(0).translateObject(0.44f,4.3f,4.6f);

        //pat
        {
            //pat
            {
                objectCars.add(objectPolice);
                objectCars.add(objectCoupe);
                objectCars.add(objectBus);
                objectCars.add(objectMonster);
                objectCars.add(objectFiretruck);
                // Plane Woi
                objectPlane.add(new Model(
                        Arrays.asList(
                                new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER),
                                new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER)
                        ),
                        new ArrayList<>(),
                        warnaYellow,
                        "resources/model/plane/plane_yellow.obj"
                ));
                objectPlane.add(new Model(
                        Arrays.asList(
                                new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER),
                                new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER)
                        ),
                        new ArrayList<>(),
                        warnaRed,
                        "resources/model/plane/plane_red.obj"
                ));
                objectPlane.add(new Model(
                        Arrays.asList(
                                new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER),
                                new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER)
                        ),
                        new ArrayList<>(),
                        warnaBlack,
                        "resources/model/plane/plane_black.obj"
                ));
                objectPlane.add(new Model(
                        Arrays.asList(
                                new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER),
                                new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER)
                        ),
                        new ArrayList<>(),
                        warnaScreen,
                        "resources/model/plane/plane_screen.obj", new Vector3f(0.4f, 0.4f, 0.4f), new Vector3f(0.9f, 0.9f, 0.9f)
                ));
                objectPlane.add(new Model(
                        Arrays.asList(
                                new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER),
                                new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER)
                        ),
                        new ArrayList<>(),
                        warnaBlack,
                        "resources/model/plane/plane_fan.obj"
                ));
                for (int i = 0; i < objectPlane.size(); i++) {
                    objectPlane.get(i).rotateObject((float) Math.toRadians(90f), 0f, 1f, 0f);
                    objectPlane.get(i).scaleObject(0.1f, 0.1f, 0.1f);
                    objectPlane.get(i).translateObjectSpesial(11.5f, 1f, 0f);
                }

                // Heli Woi
                objectHeli.add(new Model(
                        Arrays.asList(
                                new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER),
                                new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER)
                        ),
                        new ArrayList<>(),
                        warnaRed,
                        "resources/model/heli/heli_red.obj"
                ));
                objectHeli.add(new Model(
                        Arrays.asList(
                                new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER),
                                new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER)
                        ),
                        new ArrayList<>(),
                        warnaBlack,
                        "resources/model/heli/heli_black.obj"
                ));
                objectHeli.add(new Model(
                        Arrays.asList(
                                new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER),
                                new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER)
                        ),
                        new ArrayList<>(),
                        warnaScreen,
                        "resources/model/heli/heli_screen.obj", new Vector3f(0.4f, 0.4f, 0.4f), new Vector3f(0.9f, 0.9f, 0.9f)
                ));
                objectHeli.add(new Model(
                        Arrays.asList(
                                new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER),
                                new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER)
                        ),
                        new ArrayList<>(),
                        warnaBlack,
                        "resources/model/heli/heli_fan.obj"
                ));
                objectHeli.add(new Model(
                        Arrays.asList(
                                new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER),
                                new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER)
                        ),
                        new ArrayList<>(),
                        warnaWhite,
                        "resources/model/heli/heli_white.obj"
                ));
                for (int i = 0; i < objectHeli.size(); i++) {
                    objectHeli.get(i).rotateObject((float) Math.toRadians(90f), 0f, 1f, 0f);
                    objectHeli.get(i).scaleObject(0.01f, 0.01f, 0.01f);
                    objectHeli.get(i).translateObjectSpesial(11.5f, 1.5f, 0f);
                }

                // Building Woi
                objectBuilding.add(new Model(
                        Arrays.asList(
                                new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER),
                                new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER)
                        ),
                        new ArrayList<>(),
                        warnaScreen,
                        "resources/model/building/building_screen.obj"
                ));
                objectBuilding.add(new Model(
                        Arrays.asList(
                                new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER),
                                new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER)
                        ),
                        new ArrayList<>(),
                        warnaScreen,
                        "resources/model/building/building_screen2.obj"
                ));
                objectBuilding.add(new Model(
                        Arrays.asList(
                                new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER),
                                new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER)
                        ),
                        new ArrayList<>(),
                        warnaBlue,
                        "resources/model/building/building_blue.obj"
                ));
                objectBuilding.add(new Model(
                        Arrays.asList(
                                new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER),
                                new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER)
                        ),
                        new ArrayList<>(),
                        warnaGrey,
                        "resources/model/building/building_grey.obj"
                ));
                objectBuilding.add(new Model(
                        Arrays.asList(
                                new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER),
                                new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER)
                        ),
                        new ArrayList<>(),
                        warnaWhite,
                        "resources/model/building/building_white.obj"
                ));
                for (int i = 0; i < objectBuilding.size(); i++) {
                    objectBuilding.get(i).rotateObject((float) Math.toRadians(0f), 0f, 1f, 0f);
                    objectBuilding.get(i).scaleObject(0.1f, 0.1f, 0.1f);
                    objectBuilding.get(i).translateObjectSpesial(10.5f, 1f, 1f);
                }

                // Firetruck Woi
                objectFiretruck.add(new Model(
                        Arrays.asList(
                                new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER),
                                new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER)
                        ),
                        new ArrayList<>(),
                        warnaWhite,
                        "resources/model/cars/firetruck/firetruck_flight.obj"
                ));
                objectFiretruck.add(new Model(
                        Arrays.asList(
                                new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER),
                                new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER)
                        ),
                        new ArrayList<>(),
                        warnaRed,
                        "resources/model/cars/firetruck/firetruck_blight.obj"
                ));
                objectFiretruck.add(new Model(
                        Arrays.asList(
                                new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER),
                                new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER)
                        ),
                        new ArrayList<>(),
                        warnaYellow,
                        "resources/model/cars/firetruck/firetruck_yellow.obj", new Vector3f(0.8f, 0.8f, 0.8f), new Vector3f(0.8f, 0.8f, 0.8f)
                ));
                objectFiretruck.add(new Model(
                        Arrays.asList(
                                new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER),
                                new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER)
                        ),
                        new ArrayList<>(),
                        warnaBlack,
                        "resources/model/cars/firetruck/firetruck_black.obj"
                ));
                objectFiretruck.add(new Model(
                        Arrays.asList(
                                new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER),
                                new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER)
                        ),
                        new ArrayList<>(),
                        warnaBlue,
                        "resources/model/cars/firetruck/firetruck_blue.obj"
                ));
                objectFiretruck.add(new Model(
                        Arrays.asList(
                                new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER),
                                new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER)
                        ),
                        new ArrayList<>(),
                        warnaRed,
                        "resources/model/cars/firetruck/firetruck_red.obj"
                ));
                objectFiretruck.add(new Model(
                        Arrays.asList(
                                new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER),
                                new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER)
                        ),
                        new ArrayList<>(),
                        warnaScreen,
                        "resources/model/cars/firetruck/firetruck_screen.obj"
                ));
                objectFiretruck.add(new Model(
                        Arrays.asList(
                                new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER),
                                new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER)
                        ),
                        new ArrayList<>(),
                        warnaWhite,
                        "resources/model/cars/firetruck/firetruck_white.obj"
                ));
                for (int i = 0; i < objectFiretruck.size(); i++) {
                    objectFiretruck.get(i).rotateObject((float) Math.toRadians(90f), 0f, 1f, 0f);
                    objectFiretruck.get(i).scaleObject(0.1f, 0.1f, 0.1f);
                    objectFiretruck.get(i).translateObjectSpesial(11.5f, 1f, 0f);
                }

                //Bus Woi
                objectBus.add(new Model(
                        Arrays.asList(
                                new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER),
                                new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER)
                        ),
                        new ArrayList<>(),
                        warnaWhite,
                        "resources/model/cars/bus/bus_flight.obj"
                ));
                objectBus.add(new Model(
                        Arrays.asList(
                                new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER),
                                new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER)
                        ),
                        new ArrayList<>(),
                        warnaRed,
                        "resources/model/cars/bus/bus_red.obj"
                ));
                objectBus.add(new Model(
                        Arrays.asList(
                                new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER),
                                new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER)
                        ),
                        new ArrayList<>(),
                        warnaBlack,
                        "resources/model/cars/bus/bus_black.obj"
                ));
                objectBus.add(new Model(
                        Arrays.asList(
                                new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER),
                                new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER)
                        ),
                        new ArrayList<>(),
                        warnaBlue,
                        "resources/model/cars/bus/bus_blue.obj"
                ));
                objectBus.add(new Model(
                        Arrays.asList(
                                new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER),
                                new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER)
                        ),
                        new ArrayList<>(),
                        warnaScreen,
                        "resources/model/cars/bus/bus_screen.obj"
                ));
                objectBus.add(new Model(
                        Arrays.asList(
                                new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER),
                                new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER)
                        ),
                        new ArrayList<>(),
                        warnaWhite,
                        "resources/model/cars/bus/bus_white.obj"
                ));
                for (int i = 0; i < objectBus.size(); i++) {
                    objectBus.get(i).rotateObject((float) Math.toRadians(90f), 0f, 1f, 0f);
                    objectBus.get(i).scaleObject(0.1f, 0.1f, 0.1f);
                    objectBus.get(i).translateObjectSpesial(9f, 1f, 0f);
                }

                //Coupe Woi
                objectCoupe.add(new Model(
                        Arrays.asList(
                                new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER),
                                new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER)
                        ),
                        new ArrayList<>(),
                        warnaWhite,
                        "resources/model/cars/coupe/coupe_flight.obj"
                ));
                objectCoupe.add(new Model(
                        Arrays.asList(
                                new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER),
                                new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER)
                        ),
                        new ArrayList<>(),
                        warnaRed,
                        "resources/model/cars/coupe/coupe_red.obj"
                ));
                objectCoupe.add(new Model(
                        Arrays.asList(
                                new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER),
                                new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER)
                        ),
                        new ArrayList<>(),
                        warnaBlack,
                        "resources/model/cars/coupe/coupe_black.obj"
                ));
                objectCoupe.add(new Model(
                        Arrays.asList(
                                new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER),
                                new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER)
                        ),
                        new ArrayList<>(),
                        warnaYellow,
                        "resources/model/cars/coupe/coupe_blue.obj"
                ));
                objectCoupe.add(new Model(
                        Arrays.asList(
                                new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER),
                                new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER)
                        ),
                        new ArrayList<>(),
                        warnaScreen,
                        "resources/model/cars/coupe/coupe_screen.obj"
                ));
                objectCoupe.add(new Model(
                        Arrays.asList(
                                new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER),
                                new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER)
                        ),
                        new ArrayList<>(),
                        warnaWhite,
                        "resources/model/cars/coupe/coupe_white.obj"
                ));
                objectCoupe.add(new Model(
                        Arrays.asList(
                                new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER),
                                new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER)
                        ),
                        new ArrayList<>(),
                        new Vector4f(0.8f, 0.8f, 0.5f, 1f),
                        "resources/model/cars/coupe/coupe_interior.obj"
                ));
                for (int i = 0; i < objectCoupe.size(); i++) {
                    objectCoupe.get(i).rotateObject((float) Math.toRadians(90f), 0f, 1f, 0f);
                    objectCoupe.get(i).scaleObject(0.1f, 0.1f, 0.1f);
                    objectCoupe.get(i).translateObjectSpesial(8.2f, 1f, 0f);
                }

                //Monster Woi
                objectMonster.add(new Model(
                        Arrays.asList(
                                new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER),
                                new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER)
                        ),
                        new ArrayList<>(),
                        warnaWhite,
                        "resources/model/cars/monster/monster_flight.obj"
                ));
                objectMonster.add(new Model(
                        Arrays.asList(
                                new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER),
                                new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER)
                        ),
                        new ArrayList<>(),
                        warnaRed,
                        "resources/model/cars/monster/monster_red.obj"
                ));
                objectMonster.add(new Model(
                        Arrays.asList(
                                new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER),
                                new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER)
                        ),
                        new ArrayList<>(),
                        warnaBlack,
                        "resources/model/cars/monster/monster_black.obj"
                ));
                objectMonster.add(new Model(
                        Arrays.asList(
                                new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER),
                                new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER)
                        ),
                        new ArrayList<>(),
                        warnaGreen2,
                        "resources/model/cars/monster/monster_blue.obj"
                ));
                objectMonster.add(new Model(
                        Arrays.asList(
                                new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER),
                                new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER)
                        ),
                        new ArrayList<>(),
                        warnaScreen,
                        "resources/model/cars/monster/monster_screen.obj"
                ));
                objectMonster.add(new Model(
                        Arrays.asList(
                                new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER),
                                new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER)
                        ),
                        new ArrayList<>(),
                        warnaWhite,
                        "resources/model/cars/monster/monster_white.obj"
                ));
                objectMonster.add(new Model(
                        Arrays.asList(
                                new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER),
                                new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER)
                        ),
                        new ArrayList<>(),
                        warnaGrey,
                        "resources/model/cars/monster/monster_grey.obj"
                ));
                for (int i = 0; i < objectMonster.size(); i++) {
                    objectMonster.get(i).rotateObject((float) Math.toRadians(90f), 0f, 1f, 0f);
                    objectMonster.get(i).scaleObject(0.1f, 0.1f, 0.1f);
                    objectMonster.get(i).translateObjectSpesial(10.3f, 1f, 0f);
                }

                //Police Woi
                objectPolice.add(new Model(
                        Arrays.asList(
                                new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER),
                                new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER)
                        ),
                        new ArrayList<>(),
                        warnaWhite,
                        "resources/model/cars/police/police_flight.obj"
                ));
                objectPolice.add(new Model(
                        Arrays.asList(
                                new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER),
                                new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER)
                        ),
                        new ArrayList<>(),
                        warnaRed,
                        "resources/model/cars/police/police_blight.obj"
                ));
                objectPolice.add(new Model(
                        Arrays.asList(
                                new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER),
                                new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER)
                        ),
                        new ArrayList<>(),
                        warnaWhite,
                        "resources/model/cars/police/police_ban1.obj"
                ));
                objectPolice.add(new Model(
                        Arrays.asList(
                                new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER),
                                new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER)
                        ),
                        new ArrayList<>(),
                        warnaWhite,
                        "resources/model/cars/police/police_ban2.obj"
                ));
                objectPolice.add(new Model(
                        Arrays.asList(
                                new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER),
                                new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER)
                        ),
                        new ArrayList<>(),
                        warnaWhite,
                        "resources/model/cars/police/police_ban3.obj"
                ));
                objectPolice.add(new Model(
                        Arrays.asList(
                                new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER),
                                new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER)
                        ),
                        new ArrayList<>(),
                        warnaWhite,
                        "resources/model/cars/police/police_ban4.obj"
                ));
                objectPolice.add(new Model(
                        Arrays.asList(
                                new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER),
                                new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER)
                        ),
                        new ArrayList<>(),
                        warnaBlack,
                        "resources/model/cars/police/police_black.obj"
                ));
                objectPolice.add(new Model(
                        Arrays.asList(
                                new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER),
                                new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER)
                        ),
                        new ArrayList<>(),
                        warnaBlue,
                        "resources/model/cars/police/police_blue.obj"
                ));
                objectPolice.add(new Model(
                        Arrays.asList(
                                new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER),
                                new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER)
                        ),
                        new ArrayList<>(),
                        warnaRed,
                        "resources/model/cars/police/police_red.obj"
                ));
                objectPolice.add(new Model(
                        Arrays.asList(
                                new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER),
                                new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER)
                        ),
                        new ArrayList<>(),
                        warnaScreen,
                        "resources/model/cars/police/police_screen.obj"
                ));
                objectPolice.add(new Model(
                        Arrays.asList(
                                new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER),
                                new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER)
                        ),
                        new ArrayList<>(),
                        warnaWhite,
                        "resources/model/cars/police/police_white.obj"
                ));
                for (int i = 0; i < objectPolice.size(); i++) {
                    objectPolice.get(i).rotateObject((float) Math.toRadians(90f), 0f, 1f, 0f);
                    objectPolice.get(i).scaleObject(0.1f, 0.1f, 0.1f);
                    objectPolice.get(i).translateObjectSpesial(7.3f, 1f, 0f);
                }
                for (int i = 0; i < objectCars.size(); i++) {
                    for (int k = 0; k < objectCars.get(i).size(); k++) {
                        objectCars.get(i).get(k).translateObject(-objectCars.get(i).get(k).getJarakX(), -objectCars.get(i).get(k).getJarakY(), objectCars.get(i).get(k).getJarakZ());
                        objectCars.get(i).get(k).rotateObjectSpesial((float) Math.toRadians(90f), 0f, 1f, 0f);//woi z blm
                        objectCars.get(i).get(k).translateObject(objectCars.get(i).get(k).getJarakX(), objectCars.get(i).get(k).getJarakY(), -objectCars.get(i).get(k).getJarakZ());
                    }
                    rotate[i] -= 45;
                }
                for (int k = 0; k < objectPlane.size(); k++) {
                    objectPlane.get(k).translateObject(-objectPlane.get(k).getJarakX(), -objectPlane.get(k).getJarakY(), objectPlane.get(k).getJarakZ());
                    objectPlane.get(k).rotateObjectSpesial((float) Math.toRadians(90f), 0f, 1f, 0f);//woi z blm
                    objectPlane.get(k).translateObject(objectPlane.get(k).getJarakX(), objectPlane.get(k).getJarakY(), -objectPlane.get(k).getJarakZ());
                }
                rotate[5] -= 45;
                for (int k = 0; k < objectHeli.size(); k++) {
                    objectHeli.get(k).translateObject(-objectHeli.get(k).getJarakX(), -objectHeli.get(k).getJarakY(), objectHeli.get(k).getJarakZ());
                    objectHeli.get(k).rotateObjectSpesial((float) Math.toRadians(90), 0f, 1f, 0f);//woi z blm
                    objectHeli.get(k).rotateObject((float) Math.toRadians(90), 0f, 1f, 0f);//woi z blm
                    objectHeli.get(k).translateObject(objectHeli.get(k).getJarakX(), objectHeli.get(k).getJarakY(), -objectHeli.get(k).getJarakZ());
                }
                rotate[6] -= 45;
            }

            //island pat
            objectIslandPat.add(new Model(
                    Arrays.asList(
                            new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER),
                            new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER)
                    ),
                    new ArrayList<>(),
                    warnaBlue,
                    "resources/model/island_pat/island_blue1.obj"
            ));
            objectIslandPat.add(new Model(
                    Arrays.asList(
                            new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER),
                            new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER)
                    ),
                    new ArrayList<>(),
                    warnaBlue2,
                    "resources/model/island_pat/island_blue2.obj"
            ));
            objectIslandPat.add(new Model(
                    Arrays.asList(
                            new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER),
                            new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER)
                    ),
                    new ArrayList<>(),
                    warnaBrown,
                    "resources/model/island_pat/island_brown1.obj"
            ));
            objectIslandPat.add(new Model(
                    Arrays.asList(
                            new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER),
                            new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER)
                    ),
                    new ArrayList<>(),
                    warnaBrown2,
                    "resources/model/island_pat/island_brown2.obj"
            ));
            objectIslandPat.add(new Model(
                    Arrays.asList(
                            new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER),
                            new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER)
                    ),
                    new ArrayList<>(),
                    new Vector4f(0.4f, 0.3f, 0, 1),
                    "resources/model/island_pat/island_creamdark.obj"
            ));
            objectIslandPat.add(new Model(
                    Arrays.asList(
                            new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER),
                            new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER)
                    ),
                    new ArrayList<>(),
                    new Vector4f(0.7f, 0.6f, 0, 1),
                    "resources/model/island_pat/island_creamlight.obj"
            ));
            objectIslandPat.add(new Model(
                    Arrays.asList(
                            new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER),
                            new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER)
                    ),
                    new ArrayList<>(),
                    warnaGreen,
                    "resources/model/island_pat/island_green1.obj"
            ));
            objectIslandPat.add(new Model(
                    Arrays.asList(
                            new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER),
                            new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER)
                    ),
                    new ArrayList<>(),
                    warnaGreen2,
                    "resources/model/island_pat/island_green2.obj"
            ));
            objectIslandPat.add(new Model(
                    Arrays.asList(
                            new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER),
                            new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER)
                    ),
                    new ArrayList<>(),
                    warnaGrey,
                    "resources/model/island_pat/island_grey.obj"
            ));
            objectIslandPat.add(new Model(
                    Arrays.asList(
                            new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER),
                            new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER)
                    ),
                    new ArrayList<>(),
                    warnaRed,
                    "resources/model/island_pat/island_red.obj"
            ));
            objectIslandPat.add(new Model(
                    Arrays.asList(
                            new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER),
                            new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER)
                    ),
                    new ArrayList<>(),
                    warnaBlack,
                    "resources/model/lamp.obj"
            ));
            objectIslandPat.add(new Model(
                    Arrays.asList(
                            new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER),
                            new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER)
                    ),
                    new ArrayList<>(),
                    warnaYellow,
                    "resources/model/lamp2.obj", new Vector3f(0.4f, 0.4f, 0.4f), new Vector3f(0.9f, 0.9f, 0.9f)
            ));
            objectIslandPat.get(objectIslandPat.size()-1).scaleObject(0.1f,0.1f,0.1f);
            objectIslandPat.get(objectIslandPat.size()-1).translateObject(-0.75f,4.5f,-0.75f);
            objectIslandPat.get(objectIslandPat.size()-2).scaleObject(0.1f,0.1f,0.1f);
            objectIslandPat.get(objectIslandPat.size()-2).translateObject(-0.75f,4.5f,-0.75f);
            for (int i = 0; i < objectIslandPat.size(); i++) {
                objectIslandPat.get(i).scaleObject(0.9f, 0.9f, 0.9f);
                objectIslandPat.get(i).translateObjectSpesial(9f, -3.04f, -1f);
            }


            // New island pat
            objectNewIslandPat.add(new Model(
                    Arrays.asList(
                            new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER),
                            new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER)
                    ),
                    new ArrayList<>(),
                    warnaRed,
                    "resources/model/newisland/nis-red.obj"
            ));
            objectNewIslandPat.add(new Model(
                    Arrays.asList(
                            new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER),
                            new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER)
                    ),
                    new ArrayList<>(),
                    warnaYellow,
                    "resources/model/newisland/nis-yellow.obj"
            ));
            objectNewIslandPat.add(new Model(
                    Arrays.asList(
                            new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER),
                            new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER)
                    ),
                    new ArrayList<>(),
                    warnaBlack,
                    "resources/model/newisland/nis-black.obj"
            ));
            objectNewIslandPat.add(new Model(
                    Arrays.asList(
                            new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER),
                            new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER)
                    ),
                    new ArrayList<>(),
                    warnaScreen,
                    "resources/model/newisland/nis-screen.obj"
            ));
            objectNewIslandPat.add(new Model(
                    Arrays.asList(
                            new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER),
                            new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER)
                    ),
                    new ArrayList<>(),
                    warnaGrey,
                    "resources/model/newisland/nis-asap.obj"
            ));
            objectNewIslandPat.add(new Model(
                    Arrays.asList(
                            new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER),
                            new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER)
                    ),
                    new ArrayList<>(),
                    warnaWhite,
                    "resources/model/newisland/nis-white.obj"
            ));
            objectNewIslandPat.add(new Model(
                    Arrays.asList(
                            new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER),
                            new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER)
                    ),
                    new ArrayList<>(),
                    new Vector4f(0.4f, 0.2f, 0f, 1f),
                    "resources/model/newisland/nis-brown.obj"
            ));
            objectNewIslandPat.add(new Model(
                    Arrays.asList(
                            new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER),
                            new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER)
                    ),
                    new ArrayList<>(),
                    new Vector4f(0.3f, 0.1f, 0f, 1f),
                    "resources/model/newisland/nis-darkbrown.obj"
            ));
            objectNewIslandPat.add(new Model(
                    Arrays.asList(
                            new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER),
                            new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER)
                    ),
                    new ArrayList<>(),
                    warnaBlue2,
                    "resources/model/newisland/nis-blue.obj", new Vector3f(0.8f, 0.8f, 0.8f), new Vector3f(0.6f, 0.6f, 0.6f)
            ));
            objectNewIslandPat.add(new Model(
                    Arrays.asList(
                            new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER),
                            new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER)
                    ),
                    new ArrayList<>(),
                    warnaGreen,
                    "resources/model/newisland/nis-green.obj"
            ));
            objectNewIslandPat.add(new Model(
                    Arrays.asList(
                            new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER),
                            new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER)
                    ),
                    new ArrayList<>(),
                    warnaGreen2,
                    "resources/model/newisland/nis-darkgreen.obj"
            ));
            objectNewIslandPat.add(new Model(
                    Arrays.asList(
                            new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER),
                            new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER)
                    ),
                    new ArrayList<>(),
                    new Vector4f(0.6f, 0.6f, 0.6f, 1f),
                    "resources/model/newisland/nis-railbesi.obj"
            ));
            objectNewIslandPat.add(new Model(
                    Arrays.asList(
                            new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER),
                            new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER)
                    ),
                    new ArrayList<>(),
                    new Vector4f(0.35f, 0.15f, 0.0f, 1f),
                    "resources/model/newisland/nis-railkayu.obj"
            ));
            objectNewIslandPat.add(new Model(
                    Arrays.asList(
                            new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER),
                            new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER)
                    ),
                    new ArrayList<>(),
                    warnaBrown,
                    "resources/model/newisland/nis-tree.obj"
            ));
            objectNewIslandPat.add(new Model(
                    Arrays.asList(
                            new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER),
                            new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER)
                    ),
                    new ArrayList<>(),
                    warnaBlack,
                    "resources/model/lamp.obj"
            ));
            objectNewIslandPat.add(new Model(
                    Arrays.asList(
                            new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER),
                            new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER)
                    ),
                    new ArrayList<>(),
                    warnaYellow,
                    "resources/model/lamp2.obj", new Vector3f(0.4f, 0.4f, 0.4f), new Vector3f(0.9f, 0.9f, 0.9f)
            ));
            objectNewIslandPat.get(objectNewIslandPat.size()-1).scaleObject(0.1f*9/4,0.1f*9/4,0.1f*9/4);
            objectNewIslandPat.get(objectNewIslandPat.size()-1).translateObject(12.75f,-6f,-0.75f);
            objectNewIslandPat.get(objectNewIslandPat.size()-2).scaleObject(0.1f*9/4,0.1f*9/4,0.1f*9/4);
            objectNewIslandPat.get(objectNewIslandPat.size()-2).translateObject(12.75f,-6f,-0.75f);
            for (int i = 0; i < objectNewIslandPat.size(); i++) {
                objectNewIslandPat.get(i).scaleObject(0.4f, 0.4f, 0.4f);
                objectNewIslandPat.get(i).translateObjectSpesial(9f, 2.04f, 9f);
            }

            for (int i = 0; i < objectHeli.size(); i++) {
                objectHeli.get(i).translateObjectSpesial(0f, 2f, 0f);
            }
            for (int i = 0; i < objectPlane.size(); i++) {
                objectPlane.get(i).rotateObject((float) Math.toRadians(rotate[6 - 1] * 2f), 0f, 1f, 0f);
                objectPlane.get(i).translateObjectSpesial(0.4f, 0f, 0f);
                objectPlane.get(i).rotateObject((float) Math.toRadians(rotate[6 - 1] * -2f), 0f, 1f, 0f);
            }

            objectBuilding.get(0).setAmbient(new Vector3f(0.4f, 0.4f, 0.4f), new Vector3f(0.9f, 0.9f, 0.9f));
        }

        //kelvin
        for (int k = 0; k < objectTerbang2.size(); k++) {
            objectTerbang2.get(k).translateObject(-objectTerbang2.get(k).getJarakX(), -objectTerbang2.get(k).getJarakY(), objectTerbang2.get(k).getJarakZ());
            objectTerbang2.get(k).rotateObjectSpesial((float) Math.toRadians(90f), 0f, 1f, 0f);//woi z blm
            objectTerbang2.get(k).translateObject(objectTerbang2.get(k).getJarakX(), objectTerbang2.get(k).getJarakY(), -objectTerbang2.get(k).getJarakZ());
        }
        rotate[7] -= 45;

        for (int i = 0; i < objectTerbang2.size(); i++) {
            objectTerbang2.get(i).rotateObject((float) Math.toRadians(rotate[8 - 1] * 2f), 0f, 1f, 0f);
            objectTerbang2.get(i).translateObjectSpesial(0f, 0f, 0f);
            objectTerbang2.get(i).rotateObject((float) Math.toRadians(rotate[8 - 1] * -2f), 0f, 1f, 0f);
        }
    }

    boolean animate = false;
    boolean handGolem = false;
    int interval = 0;
    public void input() {
        temp = objectMainIsland.get(0).getCenterPoint();
        angle = angle % (float) Math.toRadians(360);

        objectPlane.get(4).translateObject(-objectPlane.get(4).getJarakX(), -objectPlane.get(4).getJarakY()-0.545f, objectPlane.get(4).getJarakZ());
        objectPlane.get(4).rotateObject((float)Math.toRadians(-objectPlane.get(4).getRotation()), 0f,1f,0f);
        objectPlane.get(4).rotateObject((float) Math.toRadians(19f),1f, 0f, 0f);
        objectPlane.get(4).rotateObject((float)Math.toRadians(objectPlane.get(4).getRotation()), 0f,1f,0f);
        objectPlane.get(4).translateObject(objectPlane.get(4).getJarakX(), objectPlane.get(4).getJarakY()+0.545f, -objectPlane.get(4).getJarakZ());

        objectHeli.get(3).translateObject(-objectHeli.get(3).getJarakX(), -objectHeli.get(3).getJarakY()-0.545f, objectHeli.get(3).getJarakZ());
        objectHeli.get(3).rotateObject((float) Math.toRadians(19f),0f, 1f, 0f);
        objectHeli.get(3).translateObject(objectHeli.get(3).getJarakX(), objectHeli.get(3).getJarakY()+0.545f, -objectHeli.get(3).getJarakZ());

        for (int i = 0;i<5;i++) {
            objectNewIslandPat.get(i).translateObjectSpesial(-9f, -2.04f, -9f);
            objectNewIslandPat.get(i).rotateObject((float) Math.toRadians(1),0f, -1f, 0f);
            objectNewIslandPat.get(i).translateObjectSpesial(9f, 2.04f, 9f);
        }
        for (int i =0;i<objectCars.size();i++){
            objectCars.get(i).get(0).setAmbient(new Vector3f(0.8f, 0.8f, 0.8f), new Vector3f(0.1f, 0.1f, 0.1f));
            objectCars.get(i).get(1).setAmbient(new Vector3f(0.8f, 0.8f, 0.8f), new Vector3f(0.1f, 0.1f, 0.1f));
        }

        if (window.isKeyPressed(GLFW_KEY_L) && !delay2) {
            toggleLight();
        }

        if (window.isKeyPressed(GLFW_KEY_EQUAL)) {
            spotin(objectPlane, true);
            spotin(objectBuilding, true);
            spotin(objectMonster, true);
            spotin(objectBus, true);
            spotin(objectFiretruck, true);
            spotin(objectPolice, true);
            spotin(objectCoupe, true);
            spotin(objectIslandPat, true);
            spotin(objectNewIslandPat, true);
            spotin(objectHeli, true);
            spotin(objectMainIsland, true);
            spotin(objectSecondIsland, true);
            spotin(objectThirdIsland, true);
            spotin(objectMisc, true);
            spotin(objectTerbang, true);
            spotin(objectTerbang2, true);
            spotin(objectTower, true);
        }
        if (window.isKeyPressed(GLFW_KEY_MINUS)) {
            spotin(objectPlane, false);
            spotin(objectBuilding, false);
            spotin(objectMonster, false);
            spotin(objectBus, false);
            spotin(objectFiretruck, false);
            spotin(objectPolice, false);
            spotin(objectCoupe, false);
            spotin(objectIslandPat, false);
            spotin(objectNewIslandPat, false);
            spotin(objectHeli, false);
            spotin(objectMainIsland, false);
            spotin(objectSecondIsland, false);
            spotin(objectThirdIsland, false);
            spotin(objectMisc, false);
            spotin(objectTerbang, false);
            spotin(objectTerbang2, false);
            spotin(objectTower, false);
        }

        float move = 0.1f;
        if (window.isKeyPressed(GLFW_KEY_UP)) {
            if (personBrp==3)
                camera.moveForward(move);
        }
        if (window.isKeyPressed(GLFW_KEY_DOWN)) {
            if (personBrp==3)
                camera.moveBackwards(move);
        }
        if (window.isKeyPressed(GLFW_KEY_LEFT)) {
            if (personBrp==3)
                camera.moveLeft(move);
        }
        if (window.isKeyPressed(GLFW_KEY_RIGHT)) {
            if (personBrp==3)
                camera.moveRight(move);
        }

        if (window.isKeyPressed(GLFW_KEY_O)) {
            for (Object object:objectLampu) {
                object.setLampu(false);
            }
        }

        if(mouseInput.isLeftButtonPressed()){
            Vector2f displayVec = window.getMouseInput().getDisplVec();
            camera.addRotation((float)Math.toRadians(displayVec.x * 0.2f),
                    (float)Math.toRadians(displayVec.y * 0.2f));
        }
        if(window.getMouseInput().getScroll().y != 0){
            projection.setFOV(projection.getFOV()- (window.getMouseInput().getScroll().y*0.01f));
            window.getMouseInput().setScroll(new Vector2f());
        }

        //pat input
        if (window.isKeyPressed(GLFW_KEY_L) && !delay2){
            malam = !malam;
            lampuin(objectPlane);
            lampuin(objectBuilding);
            lampuin(objectMonster);
            lampuin(objectBus);
            lampuin(objectFiretruck);
            lampuin(objectPolice);
            lampuin(objectCoupe);
            lampuin(objectIslandPat);
            lampuin(objectNewIslandPat);
            lampuin(objectHeli);
            delay2 = true;
        }

        gerakKendaraan(objectPolice, 1);
        gerakKendaraan(objectCoupe, 2);
        gerakKendaraan(objectBus, 3);
        gerakKendaraan(objectMonster, 4);
        gerakKendaraan(objectFiretruck, 5);
        gerakTerbang(objectPlane, 6);
        gerakTerbang(objectHeli, 7);

        //kelvin
        gerakTerbangKelvin(objectTerbang2, 8);

        if (window.isKeyPressed(GLFW_KEY_A)) {
            if (personBrp==1){
                camera.moveForward(3f);
                camera.addRotation((float) org.joml.Math.toRadians(0f), (float) org.joml.Math.toRadians(-2f));
                camera.moveForward(-3f);
            }
        }//woi
        if (window.isKeyPressed(GLFW_KEY_D)) {
            if (personBrp==1) {
                camera.moveForward(3f);
                camera.addRotation((float) org.joml.Math.toRadians(0f), (float) org.joml.Math.toRadians(2f));
                camera.moveForward(-3f);
            }
        }

        /*if (window.isKeyPressed(GLFW_KEY_A)){
            camera.moveLeft(0.1f);
        }
        if (window.isKeyPressed(GLFW_KEY_D)){
            camera.moveRight(0.1f);
        }
        if (window.isKeyPressed(GLFW_KEY_W)){
            camera.moveUp(0.1f);
        }
        if (window.isKeyPressed(GLFW_KEY_S)){
            camera.moveDown(0.1f);
        }*/

        if (window.isKeyPressed(GLFW_KEY_Z)){
            camera.moveForward(0.1f);

        }
        if (window.isKeyPressed(GLFW_KEY_X)){
            camera.moveBackwards(0.1f);
        }


        if (window.isKeyPressed(GLFW_KEY_1)) {
            ubahMobil(1);
        }
        if (window.isKeyPressed(GLFW_KEY_2)) {
            ubahMobil(2);
        }
        if (window.isKeyPressed(GLFW_KEY_3)) {
            ubahMobil(3);
        }
        if (window.isKeyPressed(GLFW_KEY_4)) {
            ubahMobil(4);
        }
        if (window.isKeyPressed(GLFW_KEY_5)) {
            ubahMobil(5);
        }
        if (window.isKeyPressed(GLFW_KEY_6)) {
            ubahMobil(6);
        }
        if (window.isKeyPressed(GLFW_KEY_7)) {
            ubahMobil(7);
        }
        if (window.isKeyPressed(GLFW_KEY_8)) {
            ubahMobil(8);
        }
        if (window.isKeyPressed(GLFW_KEY_C)) {
            cinematic=true;
            animate = true;
            handGolem = true;
        }
        if (window.isKeyPressed(GLFW_KEY_B)) {
            cinematic=false;
            animate=false;
            handGolem = false;
        }

        if (handGolem) {
            if (interval<15) {
                interval++;
                objectGolem.get(1).translateObject(
                        0f,
                        -0.01f,
                        0f);
//                objectGolem.get(1).rotateObjectSpesial((float) -Math.toRadians(-0.1f), 0f, 1f, 1f);
//                objectGolem.get(1).translateObject(
//                        objectGolem.get(1).getJarakX(),
//                        objectGolem.get(1).getJarakY(),
//                        objectGolem.get(1).getJarakZ());
            }
            if (interval >= 15 && interval < 30) {
                interval++;
//                objectGolem.get(1).translateObject(
//                        -objectGolem.get(1).getJarakX(),
//                        -objectGolem.get(1).getJarakY(),
//                        -objectGolem.get(1).getJarakZ());
//                objectGolem.get(1).rotateObjectSpesial((float) -Math.toRadians(0.1f), 0f, 1f, 1f);
                objectGolem.get(1).translateObject(
                        0f,
                        0.01f,
                        0f);
            }
            if (interval == 30) {
                interval = 0;
            }
        }

        //kelvin
        if(animate) {
            if (tempPlane < 280) {
                objectTerbang.get(0).translateObjectSpesial(0.0084f,0f,-0.01f);
                objectTerbang.get(1).translateObjectSpesial(0.0f,-0.001f,0.0f);
                tempPlane++;
            }

            if (tempPlane >= 280 && tempPlane < 1480) {
                objectTerbang.get(1).translateObjectSpesial(0.0f,-0.001f,0.0f);
                objectTerbang.get(0).translateObject(
                        -objectTerbang.get(0).getJarakX(),
                        -objectTerbang.get(0).getJarakY(),
                        -objectTerbang.get(0).getJarakZ());
                objectTerbang.get(0).rotateObjectSpesial((float) Math.toRadians(0.15f), 0f, 2f, 0f);
                objectTerbang.get(0).translateObject(
                        objectTerbang.get(0).getJarakX(),
                        objectTerbang.get(0).getJarakY(),
                        objectTerbang.get(0).getJarakZ());
//                System.out.println(tempPlane);
                tempPlane++;
            }

            if (tempPlane >= 1480 && tempPlane < 2680) {
                objectTerbang.get(1).translateObjectSpesial(0.0f,0.001f,0.0f);
                objectTerbang.get(0).translateObject(
                        -objectTerbang.get(0).getJarakX(),
                        -objectTerbang.get(0).getJarakY(),
                        -objectTerbang.get(0).getJarakZ());
                objectTerbang.get(0).rotateObjectSpesial((float) Math.toRadians(-0.15f), 0f, 2f, 0f);
                objectTerbang.get(0).translateObject(
                        objectTerbang.get(0).getJarakX(),
                        objectTerbang.get(0).getJarakY(),
                        objectTerbang.get(0).getJarakZ());
                tempPlane++;
            }

            if (tempPlane >= 2680 && tempPlane < 2960) {
                objectTerbang.get(0).translateObjectSpesial(-0.0084f,0f,0.01f);
                objectTerbang.get(1).translateObjectSpesial(0.0f,0.001f,0.0f);
                tempPlane++;
            }

            if (tempPlane == 2960) {
                tempPlane = 0;
            }
        }

        if (cinematic) {
            intervalCinematic++;
            if (intervalCinematic ==100) intervalCinematic=0;
            for (int i = 0; i < objectPlane.size(); i++) {
                objectPlane.get(i).rotateObject((float) Math.toRadians(rotate[6 - 1] * 2f), 0f, 1f, 0f);
                objectPlane.get(i).translateObjectSpesial(0.04f, 0f, 0f);
                objectPlane.get(i).rotateObject((float) Math.toRadians(rotate[6 - 1] * -2f), 0f, 1f, 0f);
            }
            for (int i = 0; i < objectPlane.size(); i++) {
                objectPlane.get(i).translateObject(-objectPlane.get(i).getJarakX(), -objectPlane.get(i).getJarakY(), objectPlane.get(i).getJarakZ());
                objectPlane.get(i).rotateObjectSpesial((float) Math.toRadians(2f), 0f, 1f, 0f);//woi z blm
                objectPlane.get(i).translateObject(objectPlane.get(i).getJarakX(), objectPlane.get(i).getJarakY(), -objectPlane.get(i).getJarakZ());
            }
            rotate[6-1] -= 1;


            for (int i = 0; i < objectHeli.size(); i++) {
                objectHeli.get(i).rotateObject((float) Math.toRadians(rotate[7 - 1] * 2f), 0f, 1f, 0f);
                objectHeli.get(i).translateObjectSpesial(0.02f, 0f, 0f);
                objectHeli.get(i).rotateObject((float) Math.toRadians(rotate[7 - 1] * -2f), 0f, 1f, 0f);
            }
            for (int i = 0; i < objectHeli.size(); i++) {
                objectHeli.get(i).translateObject(-objectHeli.get(i).getJarakX(), -objectHeli.get(i).getJarakY(), objectHeli.get(i).getJarakZ());
                objectHeli.get(i).rotateObjectSpesial((float) Math.toRadians(-2f), 0f, 1f, 0f);//woi z blm
                objectHeli.get(i).translateObject(objectHeli.get(i).getJarakX(), objectHeli.get(i).getJarakY(), -objectHeli.get(i).getJarakZ());
            }
            rotate[7-1] += 1;

            for (int i = 0; i < objectPolice.size(); i++) {
                objectPolice.get(i).rotateObject((float) Math.toRadians(rotate[1 - 1] * 2f), 0f, 1f, 0f);
                objectPolice.get(i).translateObjectSpesial(intervalCinematic <50?0.02f:-0.02f, 0f, 0f);
                objectPolice.get(i).rotateObject((float) Math.toRadians(rotate[1 - 1] * -2f), 0f, 1f, 0f);
                objectPolice.get(intervalCinematic<50?0:1).setAmbient(new Vector3f(0.8f,0.8f,0.8f),new Vector3f(0.8f,0.8f,0.8f));
            }


            for (int i = 0; i < objectMonster.size(); i++) {
                objectMonster.get(i).translateObject(-objectMonster.get(i).getJarakX(), -objectMonster.get(i).getJarakY(), objectMonster.get(i).getJarakZ());
                objectMonster.get(i).rotateObjectSpesial((float) Math.toRadians(-2f), 0f, 1f, 0f);//woi z blm
                objectMonster.get(i).translateObject(objectMonster.get(i).getJarakX(), objectMonster.get(i).getJarakY(), -objectMonster.get(i).getJarakZ());
            }
            rotate[4-1] += 1;

            if (intervalCinematic<25) {
                for (int i = 0; i < objectBus.size(); i++) {
                    objectBus.get(i).rotateObject((float) Math.toRadians(rotate[3 - 1] * 2f), 0f, 1f, 0f);
                    objectBus.get(i).translateObjectSpesial(0.02f, 0f, 0f);
                    objectBus.get(i).rotateObject((float) Math.toRadians(rotate[3 - 1] * -2f), 0f, 1f, 0f);
                    objectPolice.get(0).setAmbient(new Vector3f(0.8f,0.8f,0.8f),new Vector3f(0.8f,0.8f,0.8f));
                }
                for (int i = 0; i < objectBus.size(); i++) {
                    objectBus.get(i).translateObject(-objectBus.get(i).getJarakX(), -objectBus.get(i).getJarakY(), objectBus.get(i).getJarakZ());
                    objectBus.get(i).rotateObjectSpesial((float) Math.toRadians(-2f), 0f, 1f, 0f);//woi z blm
                    objectBus.get(i).translateObject(objectBus.get(i).getJarakX(), objectBus.get(i).getJarakY(), -objectBus.get(i).getJarakZ());
                }
                rotate[3-1] += 1;
            } else if (intervalCinematic<50) {
                for (int i = 0; i < objectBus.size(); i++) {
                    objectBus.get(i).rotateObject((float) Math.toRadians(rotate[3 - 1] * 2f), 0f, 1f, 0f);
                    objectBus.get(i).translateObjectSpesial(-0.02f, 0f, 0f);
                    objectBus.get(i).rotateObject((float) Math.toRadians(rotate[3 - 1] * -2f), 0f, 1f, 0f);
                    objectBus.get(1).setAmbient(new Vector3f(0.8f,0.8f,0.8f),new Vector3f(0.8f,0.8f,0.8f));
                }
                for (int i = 0; i < objectBus.size(); i++) {
                    objectBus.get(i).translateObject(-objectBus.get(i).getJarakX(), -objectBus.get(i).getJarakY(), objectBus.get(i).getJarakZ());
                    objectBus.get(i).rotateObjectSpesial((float) Math.toRadians(2f), 0f, 1f, 0f);//woi z blm
                    objectBus.get(i).translateObject(objectBus.get(i).getJarakX(), objectBus.get(i).getJarakY(), -objectBus.get(i).getJarakZ());
                }
                rotate[3-1] -= 1;
            } else if (intervalCinematic<75) {
                for (int i = 0; i < objectBus.size(); i++) {
                    objectBus.get(i).rotateObject((float) Math.toRadians(rotate[3 - 1] * 2f), 0f, 1f, 0f);
                    objectBus.get(i).translateObjectSpesial(0.02f, 0f, 0f);
                    objectBus.get(i).rotateObject((float) Math.toRadians(rotate[3 - 1] * -2f), 0f, 1f, 0f);
                    objectBus.get(0).setAmbient(new Vector3f(0.8f,0.8f,0.8f),new Vector3f(0.8f,0.8f,0.8f));
                }
                for (int i = 0; i < objectBus.size(); i++) {
                    objectBus.get(i).translateObject(-objectBus.get(i).getJarakX(), -objectBus.get(i).getJarakY(), objectBus.get(i).getJarakZ());
                    objectBus.get(i).rotateObjectSpesial((float) Math.toRadians(2f), 0f, 1f, 0f);//woi z blm
                    objectBus.get(i).translateObject(objectBus.get(i).getJarakX(), objectBus.get(i).getJarakY(), -objectBus.get(i).getJarakZ());
                }
                rotate[3-1] -= 1;
            } else {
                for (int i = 0; i < objectBus.size(); i++) {
                    objectBus.get(i).rotateObject((float) Math.toRadians(rotate[3 - 1] * 2f), 0f, 1f, 0f);
                    objectBus.get(i).translateObjectSpesial(-0.02f, 0f, 0f);
                    objectBus.get(i).rotateObject((float) Math.toRadians(rotate[3 - 1] * -2f), 0f, 1f, 0f);
                    objectBus.get(1).setAmbient(new Vector3f(0.8f,0.8f,0.8f),new Vector3f(0.8f,0.8f,0.8f));
                }
                for (int i = 0; i < objectBus.size(); i++) {
                    objectBus.get(i).translateObject(-objectBus.get(i).getJarakX(), -objectBus.get(i).getJarakY(), objectBus.get(i).getJarakZ());
                    objectBus.get(i).rotateObjectSpesial((float) Math.toRadians(-2f), 0f, 1f, 0f);//woi z blm
                    objectBus.get(i).translateObject(objectBus.get(i).getJarakX(), objectBus.get(i).getJarakY(), -objectBus.get(i).getJarakZ());
                }
                rotate[3-1] += 1;
            }
            if (intervalCinematic == 0) {
                objectBuilding.get(0).setAmbient(new Vector3f(0.4f,0.4f,0.4f), new Vector3f(0.1f,0.1f,0.1f));
                objectBuilding.get(1).setAmbient(new Vector3f(0.4f,0.4f,0.4f), new Vector3f(0.9f,0.9f,0.9f));
            } else if (intervalCinematic == 49) {
                objectBuilding.get(1).setAmbient(new Vector3f(0.4f,0.4f,0.4f), new Vector3f(0.1f,0.1f,0.1f));
                objectBuilding.get(0).setAmbient(new Vector3f(0.4f,0.4f,0.4f), new Vector3f(0.9f,0.9f,0.9f));
            }
        }
        if (window.isKeyPressed(GLFW_KEY_T)) {
            personBrp=3;
            camera.setPosition(positionCam);
            camera.setRotation(rotationCam);
            camSet=false;
            System.out.println(positionCam);
        }
        if (window.isKeyPressed(GLFW_KEY_F)) {
            if (!camSet) {
                positionCam = new Vector3f(camera.getPosition());
                rotationCam = new Vector2f(camera.getRotation());
                camSet=true;
                System.out.println(positionCam);
            }
            personBrp=1;
            if (noBrp!=6&&noBrp!=7&&noBrp!=8)
                camera.setPosition(objectCars.get(noBrp - 1).get(0).getJarakX() - 3f, 1.8f, -objectCars.get(noBrp - 1).get(0).getJarakZ());
            else if (noBrp == 6)
                camera.setPosition(objectPlane.get(0).getJarakX() - 3f, objectPlane.get(0).getJarakY()+1.3f, -objectPlane.get(0).getJarakZ());
            else if (noBrp == 7)
                camera.setPosition(objectHeli.get(0).getJarakX() - 3f, objectHeli.get(0).getJarakY()+0.7f, -objectHeli.get(0).getJarakZ());

            else if (noBrp == 8)
                camera.setPosition(objectTerbang2.get(0).getJarakX() - 3f, objectTerbang2.get(0).getJarakY()+1.3f, -objectTerbang2.get(0).getJarakZ());
            camera.setRotation((float) org.joml.Math.toRadians(0f), (float) org.joml.Math.toRadians(90f));
            camera.moveForward(3f);
            camera.addRotation((float) org.joml.Math.toRadians(0f), (float) org.joml.Math.toRadians(rotate[noBrp - 1] * 2f));
            camera.moveForward(-3f);
        }
    }

    private void gerakTerbang(ArrayList<Object> objectin, int brpp) {
        if (window.isKeyPressed(GLFW_KEY_W) && noBrp==brpp) {
            if ((apakahCollidePulau(objectin, brpp, true) || objectin.get(0).getJarakY() > 0.5f)) {
                if (apakahCollideBuilding(objectin, brpp,true) || objectin.get(0).getJarakY() > 3.1f){
                    for (int i = 0; i < objectin.size(); i++) {
                        objectin.get(i).rotateObject((float) Math.toRadians(rotate[brpp - 1] * 2f), 0f, 1f, 0f);
                        objectin.get(i).translateObjectSpesial(0.02f, 0f, 0f);
                        objectin.get(i).rotateObject((float) Math.toRadians(rotate[brpp - 1] * -2f), 0f, 1f, 0f);
                    }
                    if (personBrp == 1)
                        camera.moveForward(0.02f);
                } else {
                    toggleLight();
                }
            }
        }
        if (window.isKeyPressed(GLFW_KEY_A) && noBrp==brpp) {
            for (int i = 0; i < objectin.size(); i++) {
                objectin.get(i).translateObject(-objectin.get(i).getJarakX(), -objectin.get(i).getJarakY(), objectin.get(i).getJarakZ());
                objectin.get(i).rotateObjectSpesial((float) Math.toRadians(2f), 0f, 1f, 0f);//woi z blm
                objectin.get(i).translateObject(objectin.get(i).getJarakX(), objectin.get(i).getJarakY(), -objectin.get(i).getJarakZ());
            }
            rotate[brpp-1] -= 1;
        }
        if (window.isKeyPressed(GLFW_KEY_S) && noBrp==brpp) {
            for (int i = 0; i < objectin.size(); i++) {
                objectin.get(i).rotateObject((float) Math.toRadians(rotate[brpp - 1] * 2f), 0f, 1f, 0f);
                objectin.get(i).translateObjectSpesial(-0.02f, 0f, 0f);
                objectin.get(i).rotateObject((float) Math.toRadians(rotate[brpp - 1] * -2f), 0f, 1f, 0f);
            }
            if (personBrp==1)
                camera.moveBackwards(0.02f);
        }
        if (window.isKeyPressed(GLFW_KEY_D) && noBrp==brpp) {
            for (int i = 0; i < objectin.size(); i++) {
                objectin.get(i).translateObject(-objectin.get(i).getJarakX(), -objectin.get(i).getJarakY(), objectin.get(i).getJarakZ());
                objectin.get(i).rotateObjectSpesial((float) Math.toRadians(-2f), 0f, 1f, 0f);
                objectin.get(i).translateObject(objectin.get(i).getJarakX(), objectin.get(i).getJarakY(), -objectin.get(i).getJarakZ());
            }
            rotate[brpp-1] += 1;
        }
        if (window.isKeyPressed(GLFW_KEY_DOWN) && noBrp==brpp) {
            if (!(!apakahCollidePulau(objectin, brpp, true) && objectin.get(0).getJarakY() < 0.5f)) {
                for (int i = 0; i < objectin.size(); i++) {
                    objectin.get(i).translateObjectSpesial(0f, -0.02f,  0f);
                }
                if (personBrp==1)
                    camera.moveDown(0.02f);
            }
        }
        if (window.isKeyPressed(GLFW_KEY_UP) && noBrp==brpp) {
            for (int i = 0; i < objectin.size(); i++) {
                objectin.get(i).translateObjectSpesial(0f,0.02f, 0f);
            }
            if (personBrp==1)
                camera.moveUp(0.02f);
        }
    }

    int tempPlane = 0;
    public void loop() {
        while (window.isOpen()) {
            window.update();
            if(malam){
                glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
            } else {
                glClearColor(0.0f, 0.631f, 1.0f, 1.0f);
            }

            GL.createCapabilities();

            input();
//            System.out.println(objectTerbang2.get(0).getJarakY());
//            System.out.println((Math.pow(objectTerbang2.get(0).getJarakX()-objectTower.get(2).getJarakX()-1f, 2))
//                    +(Math.pow(objectTerbang2.get(0).getJarakZ()-objectTower.get(2).getJarakZ()-1f,2)) > 29.0f);
//        System.out.println(Math.sqrt((Math.pow(objectTerbang2.get(0).getJarakX()-objectTower.get(2).getJarakX()-1f, 2))
//                +(Math.pow(objectTerbang2.get(0).getJarakZ()-objectTower.get(2).getJarakZ()-1f,2))));
//            System.out.println(camera.getPosition());
            if (delay2){
                delayCounter2++;
            }

            if (delayCounter2 > 30){
                delayCounter2 = 0;
                delay2 = false;
            }

            // code here
            for (Object object: objectMainIsland) {
                object.draw(camera, projection);
            }

            for (Object object: objectSecondIsland) {
                object.draw(camera, projection);
            }

            for (Object object: objectThirdIsland) {
                object.draw(camera, projection);
            }

            for (Object object: objectMisc) {
                object.draw(camera, projection);
            }

            for (Object object: objectTerbang) {
                object.draw(camera, projection);
            }

            for (Object object: objectTerbang2) {
                object.draw(camera, projection);
            }

            for (Object object: objectTower) {
                object.draw(camera, projection);
            }

            for (Object object: objectLampu) {
                object.draw(camera, projection);
            }

            for (Object object: objectGolem) {
                object.draw(camera,projection);
            }

            //pat
            for (Object object: objectPlane){
                object.draw(camera, projection);
            }
            for (Object object: objectBuilding){
                object.draw(camera, projection);
            }

            for (Object object: objectFiretruck){
                object.draw(camera, projection);
            }

            for (Object object: objectMonster){
                object.draw(camera, projection);
            }

            for (Object object: objectPolice){
                object.draw(camera, projection);
            }

            for (Object object: objectBus){
                object.draw(camera, projection);
            }

            for (Object object: objectCoupe){
                object.draw(camera, projection);
            }
            for (Object object: objectIslandPat) {
                object.draw(camera, projection);
            }
            for (Object object: objectNewIslandPat) {
                object.draw(camera, projection);
            }
            for (Object object: objectHeli) {
                object.draw(camera, projection);
            }

            // Restore state
            glDisableVertexAttribArray(0);
            // Poll for window events.
            // The key callback above will only be
            // invoked during this call.
            glfwPollEvents();
        }
    }

    public static void main(String[] args) throws IOException {
        new Main().run();
    }

    //kelvin
    public boolean collideTower(ArrayList<Object> objectIn, int brpp, boolean maju) {
        boolean outp = false;
        for (int i = 0; i < objectIn.size(); i++) {
            objectIn.get(i).rotateObject((float) Math.toRadians(rotate[brpp - 1] * 2f), 0f, 1f, 0f);
            objectIn.get(i).translateObjectSpesial(maju?0.02f:-0.02f, 0f, 0f);
            objectIn.get(i).rotateObject((float) Math.toRadians(rotate[brpp - 1] * -2f), 0f, 1f, 0f);
        }

        if (Math.sqrt((Math.pow(objectTerbang2.get(0).getJarakX()-objectTower.get(2).getJarakX()-1f, 2))
                +(Math.pow(objectTerbang2.get(0).getJarakZ()-objectTower.get(2).getJarakZ()-1f,2))) > 5.1f) {
            outp = true;
        }

        for (int i = 0; i < objectIn.size(); i++) {
            objectIn.get(i).rotateObject((float) Math.toRadians(rotate[brpp - 1] * 2f), 0f, 1f, 0f);
            objectIn.get(i).translateObjectSpesial(maju?-0.02f:0.02f, 0f, 0f);
            objectIn.get(i).rotateObject((float) Math.toRadians(rotate[brpp - 1] * -2f), 0f, 1f, 0f);
        }
//        System.out.println(outp);
        return outp;
    }
    private void gerakTerbangKelvin(ArrayList<Object> objectin, int brpp) {
        if (window.isKeyPressed(GLFW_KEY_W) && noBrp==brpp) {
//            System.out.println(collideTower(objectin,brpp,true));
            if (!collideTower(objectin,brpp,true)) {
                for (int i = 0; i < objectin.size(); i++) {
                    objectin.get(i).rotateObject((float) Math.toRadians(rotate[brpp - 1] * 2f), 0f, 1f, 0f);
                    objectin.get(i).translateObjectSpesial(0.02f, 0f, 0f);
                    objectin.get(i).rotateObject((float) Math.toRadians(rotate[brpp - 1] * -2f), 0f, 1f, 0f);
                }
                if (personBrp == 1)
                    camera.moveForward(0.02f);
            }
        }
        if (window.isKeyPressed(GLFW_KEY_A) && noBrp==brpp) {
            for (int i = 0; i < objectin.size(); i++) {
                objectin.get(i).translateObject(-objectin.get(i).getJarakX(), -objectin.get(i).getJarakY(), objectin.get(i).getJarakZ());
                objectin.get(i).rotateObjectSpesial((float) Math.toRadians(2f), 0f, 1f, 0f);//woi z blm
                objectin.get(i).translateObject(objectin.get(i).getJarakX(), objectin.get(i).getJarakY(), -objectin.get(i).getJarakZ());
            }
            rotate[brpp-1] -= 1;
        }
        if (window.isKeyPressed(GLFW_KEY_S) && noBrp==brpp) {
            for (int i = 0; i < objectin.size(); i++) {
                objectin.get(i).rotateObject((float) Math.toRadians(rotate[brpp - 1] * 2f), 0f, 1f, 0f);
                objectin.get(i).translateObjectSpesial(-0.02f, 0f, 0f);
                objectin.get(i).rotateObject((float) Math.toRadians(rotate[brpp - 1] * -2f), 0f, 1f, 0f);
            }
            if (personBrp==1)
                camera.moveBackwards(0.02f);
        }
        if (window.isKeyPressed(GLFW_KEY_D) && noBrp==brpp) {
            for (int i = 0; i < objectin.size(); i++) {
                objectin.get(i).translateObject(-objectin.get(i).getJarakX(), -objectin.get(i).getJarakY(), objectin.get(i).getJarakZ());
                objectin.get(i).rotateObjectSpesial((float) Math.toRadians(-2f), 0f, 1f, 0f);
                objectin.get(i).translateObject(objectin.get(i).getJarakX(), objectin.get(i).getJarakY(), -objectin.get(i).getJarakZ());
            }
            rotate[brpp-1] += 1;
        }
        if (window.isKeyPressed(GLFW_KEY_DOWN) && noBrp==brpp) {
            if (!(!apakahCollidePulau(objectin, brpp, true) && objectin.get(0).getJarakY() < 0.5f)) {
                for (int i = 0; i < objectin.size(); i++) {
                    objectin.get(i).translateObjectSpesial(0f, -0.02f,  0f);
                }
                if (personBrp==1)
                    camera.moveDown(0.02f);
            }
        }
        if (window.isKeyPressed(GLFW_KEY_UP) && noBrp==brpp) {
            for (int i = 0; i < objectin.size(); i++) {
                objectin.get(i).translateObjectSpesial(0f,0.02f, 0f);
            }
            if (personBrp==1)
                camera.moveUp(0.02f);
        }
    }

    public void spotin(ArrayList<Object> objectin, boolean spot) {
        for (Object object: objectin){
            object.setSpot(spot);
            for(Object objectChild: object.getChildObject()){
                objectChild.setSpot(spot);
            }
        }
        System.out.println(objectin.get(0).getSpot());
    }
    public void lampuin(ArrayList<Object> objectin) {
        for (Object object: objectin){
            object.setScene(malam);
            for(Object objectChild: object.getChildObject()){
                objectChild.setScene(malam);
            }
        }
    }

    public boolean apakahCollide(ArrayList<Object> objectIn, float jarakk, int brpp, boolean maju) {
        boolean outp = false;
        for (int k = 0; k < objectCars.size(); k++) {
            if (k == brpp -1) continue;
            float jarak;
            if (k == 2 || k == 4 || brpp == 3 || brpp == 5) jarak = 0.4f;
            else jarak = 0.25f;
            for (int i = 0; i < objectIn.size(); i++) {
                objectIn.get(i).rotateObject((float) Math.toRadians(rotate[brpp - 1] * 2f), 0f, 1f, 0f);
                objectIn.get(i).translateObjectSpesial(maju?0.02f:-0.02f, 0f, 0f);
                objectIn.get(i).rotateObject((float) Math.toRadians(rotate[brpp - 1] * -2f), 0f, 1f, 0f);
            }
            if (Math.sqrt(Math.pow(objectIn.get(0).getJarakX()- objectCars.get(k).get(0).getJarakX(), 2)+Math.pow(objectIn.get(0).getJarakZ()-objectCars.get(k).get(0).getJarakZ(), 2)) < jarak) {
                outp = true;
            }
            for (int i = 0; i < objectIn.size(); i++) {
                objectIn.get(i).rotateObject((float) Math.toRadians(rotate[brpp - 1] * 2f), 0f, 1f, 0f);
                objectIn.get(i).translateObjectSpesial(maju?-0.02f:0.02f, 0f, 0f);
                objectIn.get(i).rotateObject((float) Math.toRadians(rotate[brpp - 1] * -2f), 0f, 1f, 0f);
            }
        }
        return (outp);
    }

    public boolean apakahCollidePulau(ArrayList<Object> objectIn, int brpp, boolean maju) {
        boolean outp = false;
        for (int k = 0; k < objectCars.size(); k++) {
            for (int i = 0; i < objectIn.size(); i++) {
                objectIn.get(i).rotateObject((float) Math.toRadians(rotate[brpp - 1] * 2f), 0f, 1f, 0f);
                objectIn.get(i).translateObjectSpesial(maju?0.02f:-0.02f, 0f, 0f);
                objectIn.get(i).rotateObject((float) Math.toRadians(rotate[brpp - 1] * -2f), 0f, 1f, 0f);
            }
            if (Math.sqrt((Math.pow(objectIn.get(0).getJarakX()-objectIslandPat.get(0).getJarakX()-0.2, 2))
                    +(Math.pow(objectIn.get(0).getJarakZ()-objectIslandPat.get(0).getJarakZ()-0.6,2))) > 2.55f) {
                outp = true;
            }

            for (int i = 0; i < objectIn.size(); i++) {
                objectIn.get(i).rotateObject((float) Math.toRadians(rotate[brpp - 1] * 2f), 0f, 1f, 0f);
                objectIn.get(i).translateObjectSpesial(maju?-0.02f:0.02f, 0f, 0f);
                objectIn.get(i).rotateObject((float) Math.toRadians(rotate[brpp - 1] * -2f), 0f, 1f, 0f);
            }
        }
        return (outp);
    }

    public boolean apakahCollideBuilding(ArrayList<Object> objectIn, int brpp, boolean maju) {
        boolean outp = false;
        for (int k = 0; k < objectCars.size(); k++) {
            for (int i = 0; i < objectIn.size(); i++) {
                objectIn.get(i).rotateObject((float) Math.toRadians(rotate[brpp - 1] * 2f), 0f, 1f, 0f);
                objectIn.get(i).translateObjectSpesial(maju?0.02f:-0.02f, 0f, 0f);
                objectIn.get(i).rotateObject((float) Math.toRadians(rotate[brpp - 1] * -2f), 0f, 1f, 0f);
            }
            if (Math.sqrt((Math.pow(objectIn.get(0).getJarakX()-objectBuilding.get(0).getJarakX(), 2))
                    +(Math.pow(objectIn.get(0).getJarakZ()-objectBuilding.get(0).getJarakZ()+1.1,2))) > .5f) {
                outp = true;
            }

            for (int i = 0; i < objectIn.size(); i++) {
                objectIn.get(i).rotateObject((float) Math.toRadians(rotate[brpp - 1] * 2f), 0f, 1f, 0f);
                objectIn.get(i).translateObjectSpesial(maju?-0.02f:0.02f, 0f, 0f);
                objectIn.get(i).rotateObject((float) Math.toRadians(rotate[brpp - 1] * -2f), 0f, 1f, 0f);
            }
        }
//        System.out.println(outp);
        return (outp);
    }

    public void gerakKendaraan(ArrayList<Object> objectin, int brpp) {
        if (window.isKeyPressed(GLFW_KEY_W) && noBrp==brpp) {
            if (!apakahCollide(objectin,0.5f, brpp, true)) {

                if (!apakahCollidePulau(objectin, brpp, true)){
                    if(apakahCollideBuilding(objectin, brpp,true)) {
                        for (int i = 0; i < objectin.size(); i++) {
                            objectin.get(i).rotateObject((float) Math.toRadians(rotate[brpp - 1] * 2f), 0f, 1f, 0f);
                            objectin.get(i).translateObjectSpesial(0.02f, 0f, 0f);
                            objectin.get(i).rotateObject((float) Math.toRadians(rotate[brpp - 1] * -2f), 0f, 1f, 0f);
                        }
                        if (personBrp == 1)
                            camera.moveForward(0.02f);
                        objectin.get(0).setAmbient(new Vector3f(0.8f, 0.8f, 0.8f), new Vector3f(0.8f,0.8f, 0.8f));
                    }
                }
            }
        }
        if (window.isKeyPressed(GLFW_KEY_A) && noBrp==brpp) {
            for (int i = 0; i < objectin.size(); i++) {
                objectin.get(i).translateObject(-objectin.get(i).getJarakX(), -objectin.get(i).getJarakY(), objectin.get(i).getJarakZ());
                objectin.get(i).rotateObjectSpesial((float) Math.toRadians(2f), 0f, 1f, 0f);//woi z blm
                objectin.get(i).translateObject(objectin.get(i).getJarakX(), objectin.get(i).getJarakY(), -objectin.get(i).getJarakZ());
            }
            rotate[brpp-1] -= 1;
        }
        if (window.isKeyPressed(GLFW_KEY_S) && noBrp==brpp) {
            if (!apakahCollide(objectin,0.5f, brpp, false)) {
                if (!apakahCollidePulau(objectin, brpp, false)) {
                    if (apakahCollideBuilding(objectin, brpp, false)) {
                        for (int i = 0; i < objectin.size(); i++) {
                            objectin.get(i).rotateObject((float) Math.toRadians(rotate[brpp - 1] * 2f), 0f, 1f, 0f);
                            objectin.get(i).translateObjectSpesial(-0.02f, 0f, 0f);
                            objectin.get(i).rotateObject((float) Math.toRadians(rotate[brpp - 1] * -2f), 0f, 1f, 0f);
                        }
                        if (personBrp == 1)
                            camera.moveBackwards(0.02f);
                        objectin.get(1).setAmbient(new Vector3f(0.8f, 0.8f, 0.8f), new Vector3f(0.8f,0.8f, 0.8f));
                    }
                }
            }
        }
        if (window.isKeyPressed(GLFW_KEY_D) && noBrp==brpp) {
            for (int i = 0; i < objectin.size(); i++) {
                objectin.get(i).translateObject(-objectin.get(i).getJarakX(), -objectin.get(i).getJarakY(), objectin.get(i).getJarakZ());
                objectin.get(i).rotateObjectSpesial((float) Math.toRadians(-2f), 0f, 1f, 0f);
                objectin.get(i).translateObject(objectin.get(i).getJarakX(), objectin.get(i).getJarakY(), -objectin.get(i).getJarakZ());
            }
            rotate[brpp-1] += 1;
        }
    }

    private void ubahMobil(int noBrpp) {
        if (personBrp == 1) {
            camera.moveForward(3f);
            camera.addRotation((float) org.joml.Math.toRadians(0f), (float) org.joml.Math.toRadians(rotate[noBrp - 1] * -2f));
            camera.moveForward(-3f);
        }

        noBrp = noBrpp;
        if (personBrp == 1) {
            if (noBrp!=6&&noBrp!=7&&noBrp!=8)
                camera.setPosition(objectCars.get(noBrp - 1).get(0).getJarakX() - 3f, 1.8f, -objectCars.get(noBrp - 1).get(0).getJarakZ());
            else if (noBrp == 6)
                camera.setPosition(objectPlane.get(0).getJarakX() - 3f, objectPlane.get(0).getJarakY()+1.3f, -objectPlane.get(0).getJarakZ());
            else if (noBrp == 7)
                camera.setPosition(objectHeli.get(0).getJarakX() - 3f, objectHeli.get(0).getJarakY()+0.7f, -objectHeli.get(0).getJarakZ());

            else if (noBrp == 8)
                camera.setPosition(objectTerbang2.get(0).getJarakX() - 3f, objectTerbang2.get(0).getJarakY()+1.3f, -objectTerbang2.get(0).getJarakZ());
            camera.setRotation(0f,(float) org.joml.Math.toRadians(90f));

            camera.moveForward(3f);
            camera.addRotation((float) org.joml.Math.toRadians(0f), (float) org.joml.Math.toRadians(rotate[noBrp - 1] * 2f));
            camera.moveForward(-3f);
        }
    }

    private void toggleLight() {
        malam = !malam;
        if (malam) {
            objectLampu.get(10).setColor(new Vector4f(0.7f,0.7f,0.7f,0f));
        }
        else {
            objectLampu.get(10).setColor(new Vector4f(0.9f,1f,0.1f,0f));
        }

        for (Object object : objectMainIsland) {
            object.setScene(malam);
            for (Object objectChild : object.getChildObject()) {
                objectChild.setScene(malam);
            }
        }

        for (Object object : objectSecondIsland) {
            object.setScene(malam);
            for (Object objectChild : object.getChildObject()) {
                objectChild.setScene(malam);
            }
        }

        for (Object object : objectThirdIsland) {
            object.setScene(malam);
            for (Object objectChild : object.getChildObject()) {
                objectChild.setScene(malam);
            }
        }

        for (Object object : objectMisc) {
            object.setScene(malam);
            for (Object objectChild : object.getChildObject()) {
                objectChild.setScene(malam);
            }
        }

        for (Object object : objectTerbang) {
            object.setScene(malam);
            for (Object objectChild : object.getChildObject()) {
                objectChild.setScene(malam);
            }
        }

        for (Object object : objectTerbang2) {
            object.setScene(malam);
            for (Object objectChild : object.getChildObject()) {
                objectChild.setScene(malam);
            }
        }

        for (Object object : objectTower) {
            object.setScene(malam);
            for (Object objectChild : object.getChildObject()) {
                objectChild.setScene(malam);
            }
        }

        lampuin(objectPlane);
        lampuin(objectBuilding);
        lampuin(objectMonster);
        lampuin(objectBus);
        lampuin(objectFiretruck);
        lampuin(objectPolice);
        lampuin(objectCoupe);
        lampuin(objectIslandPat);
        lampuin(objectHeli);
        delay2 = true;
    }
}
