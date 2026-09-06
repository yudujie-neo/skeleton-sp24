package adventure;

import java.util.HashMap;
import java.util.Map;

public class FillerStage implements AdventureStage {
    private final String prompt;
    private final Map<String, AdventureStage> responses;

    /**
     * Constructor for filler stage at end of game (No responses).
     * 中文：构造位于游戏末尾的过渡关卡（没有可选回应）。
     *
     * @param prompt Prompt for fillerStage. 中文：过渡关卡显示的提示文本。
     */
    public FillerStage(String prompt) {
        this(prompt, new HashMap<>());
    }

    /**
     * constructor for filler stage in middle of game.
     * 中文：构造位于游戏中间的过渡关卡。
     *
     * @param prompt      prompt for fillerStage. 中文：过渡关卡显示的提示文本。
     * @param responses   responses for fillerStage. 中文：该关卡允许的回应及后续关卡。
     */
    public FillerStage(String prompt, Map<String, AdventureStage> responses) {
        this.prompt = prompt;
        this.responses = responses;
    }

    /**
     * Plays stage.
     * Filler stages do nothing, but display their prompt, so this does nothing.
     * 中文：运行关卡。过渡关卡只显示提示，不执行其他操作，因此此方法为空。
     */
    @Override
    public void playStage() {}

    @Override
    public String nextStagePrompt() {
        return this.prompt;
    }

    @Override
    public Map<String, AdventureStage> getResponses() {
        return this.responses;
    }

}
