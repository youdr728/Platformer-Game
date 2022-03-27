package se.liu.joeri765youdr728.platformer.game;
/**
 * @author      Johannes Eriksson <joeri765 @ student.liu.se>
 * @author      Yousef Drgham <youdr728 @ student.liu.se>
 * @version     1.0
 * @since       1.0
 */
public enum EntityType
{
    WALL, PLATFORM, PLAYER, OBSTACLE, GOAL, COINS, POWER_UP_TIME, POWER_UP_JUMP, POWER_UP_SPEED, ENEMY, ENEMY_ATTACK;


    public static EntityType getEntityType(int n){
        EntityType result = null;

        EntityType wall = EntityType.WALL;
        EntityType platform = EntityType.PLATFORM;
        EntityType player = EntityType.PLAYER;
        EntityType obstacle = EntityType.OBSTACLE;
        EntityType goal = EntityType.GOAL;
        EntityType coins = EntityType.COINS;
        EntityType timePowerUp = EntityType.POWER_UP_TIME;
        EntityType jumpPowerUp = EntityType.POWER_UP_JUMP;
        EntityType speedPowerUp = EntityType.POWER_UP_SPEED;
        EntityType enemy = EntityType.ENEMY;
        EntityType enemyAttack = EntityType.ENEMY_ATTACK;

        for (EntityType type:EntityType.values()) {
            if(type.ordinal() == n){
                result = type;
            }
        }
        return result;
    }

}
