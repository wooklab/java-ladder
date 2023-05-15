package nextstep.ladder;

import nextstep.ladder.domain.ladder.Ladder;
import nextstep.ladder.domain.ladder.LadderHeight;
import nextstep.ladder.domain.ladder.Lines;
import nextstep.ladder.domain.user.ExecuteResults;
import nextstep.ladder.domain.user.Participants;
import nextstep.ladder.domain.user.Result;
import nextstep.ladder.domain.user.UserName;
import nextstep.ladder.view.InputView;
import nextstep.ladder.view.ResultView;

public class LadderApplication {

    public static void main(String[] args) {
        Participants participants = Participants.of(InputView.showUserNamesConsole());
        ExecuteResults executeResults = ExecuteResults.of(InputView.showExecuteResultsConsole());
        LadderHeight ladderHeight = LadderHeight.from(InputView.showLadderHeightConsole());

        Ladder ladder = new Ladder(Lines.create(ladderHeight.getHeight(), participants.count()));

        ResultView.drawInputResult(participants, executeResults, ladder);
        execute(participants, executeResults, ladder);
    }

    private static void execute(Participants participants, ExecuteResults executeResults, Ladder ladder) {
        executeResults.execute(participants, ladder);

        RepeatStatus repeatStatus = new RepeatStatus(true);
        while (repeatStatus.repeatable()) {
            drawUserResult(executeResults, repeatStatus);
        }
    }

    private static void drawUserResult(ExecuteResults executeResults, RepeatStatus repeatStatus) {
        UserName inputUserName = UserName.add(InputView.showUserResultConsole());
        if (inputUserName.isAll()) {
            repeatStatus.stop();
            ResultView.drawUserResult(executeResults.toArray());
            return;
        }
        Result result = executeResults.getResult(inputUserName);
        ResultView.drawUserResult(result.getValue());
    }

    static class RepeatStatus {

        private boolean repeat;

        public RepeatStatus(boolean repeat) {
            this.repeat = repeat;
        }

        public boolean repeatable() {
            return repeat;
        }

        public void stop() {
            repeat = false;
        }

    }

}
