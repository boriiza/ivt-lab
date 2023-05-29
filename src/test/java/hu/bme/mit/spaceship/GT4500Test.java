package hu.bme.mit.spaceship;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

public class GT4500Test {

  private TorpedoStore primaryMock;
  private TorpedoStore secondaryMock;
  private GT4500 ship;

  @BeforeEach
  public void init(){
    primaryMock = mock(TorpedoStore.class);
    secondaryMock = mock(TorpedoStore.class);
    this.ship = new GT4500(primaryMock, secondaryMock);
  }

  @Test
  public void fireTorpedo_Single_Success(){
    // Arrange
    when(primaryMock.fire(1)).thenReturn(true);
    when(secondaryMock.fire(1)).thenReturn(true);

    // Act
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
    verify(primaryMock, times(1)).fire(1);
    verify(secondaryMock, times(0)).fire(1);
  }

  @Test
  public void fireTorpedo_All_Success(){
    // Arrange
    when(primaryMock.fire(1)).thenReturn(true);
    when(secondaryMock.fire(1)).thenReturn(true);

    // Act
    boolean result = ship.fireTorpedo(FiringMode.ALL);

    // Assert
    assertEquals(true, result);
    verify(primaryMock, times(1)).fire(1);
    verify(secondaryMock, times(1)).fire(1);
  }

  //Five new test cases according to the Test_plan file

  @Test
  public void fireTorpedo_Test_1(){
    // Arrange
    when(primaryMock.fire(1)).thenReturn(false);

    // Act
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
    verify(primaryMock, times(1)).fire(1);
    verify(secondaryMock, times(0)).fire(1);
  }

  @Test
  public void fireTorpedo_Test_2(){
    // Arrange
    when(primaryMock.fire(1)).thenReturn(false);
    when(secondaryMock.isEmpty()).thenReturn(true);

    // Act
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);
    result = ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
    verify(primaryMock, times(2)).fire(1);
    verify(secondaryMock, times(0)).fire(1);
  }

  @Test
  public void fireTorpedo_Test_3(){
    // Arrange
    when(primaryMock.fire(1)).thenReturn(false);
    when(secondaryMock.fire(1)).thenReturn(false);

    // Act
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);
    result = ship.fireTorpedo(FiringMode.SINGLE);
    result = ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
    verify(primaryMock, times(2)).fire(1);
    verify(secondaryMock, times(1)).fire(1);
  }

  @Test
  public void fireTorpedo_Test_4(){
    // Arrange
    when(primaryMock.fire(1)).thenReturn(false);
    when(secondaryMock.fire(1)).thenReturn(false);

    // Act
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);
    result = ship.fireTorpedo(FiringMode.SINGLE);
    result = ship.fireTorpedo(FiringMode.ALL);

    // Assert
    verify(primaryMock, times(2)).fire(1);
    verify(secondaryMock, times(2)).fire(1);
  }

  @Test
  public void fireTorpedo_Test_5(){
    // Arrange
    when(primaryMock.fire(1)).thenReturn(false);
    when(secondaryMock.fire(1)).thenReturn(true);

    // Act
    boolean result = ship.fireTorpedo(FiringMode.ALL);

    // Assert
    verify(primaryMock, times(1)).fire(1);
    verify(secondaryMock, times(1)).fire(1);
    assertEquals(true, result);
  }

  @Test
  public void fireTorpedo_Test_6(){
    // Arrange
    when(primaryMock.isEmpty()).thenReturn(true);

    // Act
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
    verify(primaryMock, times(0)).fire(1);
    verify(secondaryMock, times(1)).fire(1);
  }

}
