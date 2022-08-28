package frankie.financebudget.entities.entities.objects;

import java.util.List;

public class CompressedEntries {
    double outgoing;
    double incoming;
    List<Entry> entries;

    public CompressedEntries(
            double outgoing,
            double incoming,
            List<Entry> entries
    ) {
        this.outgoing = outgoing;
        this.incoming = incoming;
        this.entries = entries;
    }

    public CompressedEntries() {}


    public double getOutgoing() {
        return outgoing;
    }

    public void setOutgoing(double outgoing) {
        this.outgoing = outgoing;
    }

    public double getIncoming() {
        return incoming;
    }

    public void setIncoming(double incoming) {
        this.incoming = incoming;
    }

    public List<Entry> getEntries() {
        return entries;
    }

    public void setEntries(List<Entry> entries) {
        this.entries = entries;
    }
}
