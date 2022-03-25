package se.liu.joeri765youdr728.platformer.game;

import java.awt.*;
/**
 * @author      Johannes Eriksson <joeri765 @ student.liu.se>
 * @author      Yousef Drgham <youdr728 @ student.liu.se>
 * @version     1.0
 * @since       1.0
 */
public interface Entity
{
    int getX();

    int getY();

    int getHeight();

    int getWidth();

    int getCollisionX();

    int getCollisionY();

    int getCollisionWidth();

    int getCollisionHeight();

    Rectangle getRectangle();

    EntityType getEntityType();
}
