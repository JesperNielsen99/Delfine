package userinterface;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class UserInterfaceTest {
    private UserInterface userInterface;


    @BeforeEach
    public void setup(){
        userInterface = new UserInterface();
    }

    @Test
    void calculateSwimTime() {
        double expectedTime = 70.10;

        double actualTime = userInterface.calculateSwimTime("1:10.10");

        assertEquals(expectedTime, actualTime);
    }
}