package actucalCombat;

public class BackupA implements Runnable {
    private Backup backup;

    BackupA(Backup backup) {
        this.backup = backup;
    }

    public void run() {
        backup.backupA();
    }
}
