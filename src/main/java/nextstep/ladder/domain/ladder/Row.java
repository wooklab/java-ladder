package nextstep.ladder.domain.ladder;

import nextstep.ladder.domain.generator.BooleanGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class Row {

    private final List<ConnectionType> connectionTypes;
    private final int connectionSize;

    public Row(List<ConnectionType> connectionTypes, int connectionSize) {
        this.connectionTypes = connectionTypes;
        this.connectionSize = connectionSize;
    }

    public static Row initialize(int size, BooleanGenerator booleanGenerator) {
        Row row = new Row(new ArrayList<>(), size);
        IntStream.range(0, size)
                .forEach(index -> row.addConnectionType(index, booleanGenerator));
        return row;
    }

    private void addConnectionType(int index, BooleanGenerator booleanGenerator) {
        if (isFirst(index)) {
            connectionTypes.add(ConnectionType.getRightOrNone(booleanGenerator.getBoolean()));
            return;
        }

        ConnectionType beforeConnection = connectionTypes.get(index - 1);
        if (beforeConnection.isRight()) {
            connectionTypes.add(ConnectionType.LEFT);
            return;
        }

        if (isLast(index)) {
            connectionTypes.add(ConnectionType.NONE);
            return;
        }

        connectionTypes.add(ConnectionType.getRightOrNone(booleanGenerator.getBoolean()));
    }

    private boolean isFirst(int index) {
        return index == 0;
    }

    private boolean isLast(int index) {
        return index == connectionSize - 1;
    }

    public List<ConnectionType> getConnectionTypes() {
        return connectionTypes;
    }

    public int getMovePoint(int currentPosition) {
        ConnectionType currenConnectionType = connectionTypes.get(currentPosition);
        return currenConnectionType.getMovePoint();
    }

}
