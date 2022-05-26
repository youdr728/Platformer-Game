package se.liu.joeri765youdr728.platformer.game;

import java.awt.*;
/**
 * @author      Johannes Eriksson <joeri765 @ student.liu.se>
 * @author      Yousef Drgham <youdr728 @ student.liu.se>
 * @version     1.0
 * @since       1.0
 *
 * Interface for the functions that every entity needs like its position and dimensions
 */
public interface Entity
{
    int getX();

    int getY();

    int getHeight();

    int getWidth();

    Rectangle getRectangle();

    EntityType getEntityType();
}
