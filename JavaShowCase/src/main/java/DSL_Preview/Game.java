package DSL_Preview;

import static DSL_Preview.PlayerDetail.*;
// This is a showcase of creating a domain specific language and being able to run it without any warnings
public interface Game {
    static IF1 create() {
        return null;
    }

    public static void main(String[] args) {
        Game g = Game.create()
                .player("Lara")
                .adversary(Game.create()
                        .player(name("La")).
                        get())
                .player(name("Hanna")).setInSpace()
                .player(name("Lisa"), age(22))
                .get();
    }
}
