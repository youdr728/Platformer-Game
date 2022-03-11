package se.liu.joeri765youdr728.Platformer;

import java.awt.*;

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
