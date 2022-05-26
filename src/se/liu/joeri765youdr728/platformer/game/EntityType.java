package se.liu.joeri765youdr728.platformer.game;
/**
 * @author      Johannes Eriksson <joeri765 @ student.liu.se>
 * @author      Yousef Drgham <youdr728 @ student.liu.se>
 * @version     1.0
 * @since       1.0
 *
 * Enum class for all the different entitys in the game
 */
public enum EntityType
{
    WALL, PLATFORM, PLAYER, OBSTACLE, GOAL, COINS, POWER_UP_TIME, POWER_UP_JUMP, POWER_UP_SPEED, ENEMY, ENEMY_ATTACK;


    public static EntityType getEntityType(int index){
        //Returns the entity type based on what index you use as parameter
        EntityType result = null;

        for (EntityType type:EntityType.values()) {
            if(type.ordinal() == index){
                result = type;
            }
        }
        return result;
    }

}
