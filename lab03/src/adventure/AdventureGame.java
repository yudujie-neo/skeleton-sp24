package adventure;

import edu.princeton.cs.algs4.In;

import java.util.Map;

public class AdventureGame {

    public In in;
    private AdventureStage currentStage;

    public AdventureGame(In in) {
        this(in, new FillerStage("""
                        It's a wonderful day of learning about computer science! We are going to see so many cool things today!
                        Let's go! (To answer the prompts, type the possibilities in the brackets to go to that choice)"
                        """,
                        Map.of("go", new BeeCountingStage(in))
                )
        );
    }

    AdventureGame(In in, AdventureStage firstStage) {
        this.in = in;
        this.currentStage = firstStage;
    }

    /**
     * runs the stage and its puzzles.
     * 中文：运行当前关卡及其中的谜题。
     */
    void handleStage() {
        this.currentStage.playStage();
        System.out.println(this);
        if (this.currentStage.getResponses().isEmpty()) {
            this.currentStage = null;
            return;
        }
        AdventureStage poss;
        while (true) {
            poss = this.parseResponse(in.readLine());
            if (poss != null) {
                break;
            }

            System.out.println("Sorry, I don't understand that. Please type one of the responses in the brackets!");
        }
        this.currentStage = poss;
    }

    private AdventureStage parseResponse(String response) {
        // If empty then prompt again
        // 中文：输入为空时重新提示。
        if (response == null || response.isEmpty()) {
            return null;
        }

        // First attempt exact match
        // 中文：首先尝试精确匹配。
        if (this.currentStage.getResponses().containsKey(response.toLowerCase())) {
            return this.currentStage.getResponses().get(response.toLowerCase());
        }

        // Then, look for contained matches
        // 中文：随后查找包含关系的匹配项。
        Map<String, AdventureStage> responses = this.currentStage.getResponses();
        for (Map.Entry<String, AdventureStage> other : responses.entrySet()) {
            if (other.getKey().toLowerCase().contains(response.toLowerCase())) {
                return other.getValue();
            }
        }
        return null;
    }

    /**
     * driving function of game.
     * Plays until the current stage has no responses, then ends.
     * 中文：游戏的驱动方法。持续运行，直到当前关卡没有可选回应时结束。
     */
    public void play() {
        while (this.currentStage != null) {
            handleStage();
        }
        System.out.println("All in another fun day of learning computer science :)");
    }

    @Override
    public String toString() {
        String result = this.currentStage.nextStagePrompt() + "\n";
        Map<String, AdventureStage> responses = this.currentStage.getResponses();
        if (!responses.isEmpty()) {
            result += ">>";
            for (String response : responses.keySet()) {
                result += " [" + response + "]";
            }
        }
        return result;
    }

    public static void main(String[] args) {
        AdventureGame adventure = new AdventureGame(new In());
        adventure.play();
    }

}
