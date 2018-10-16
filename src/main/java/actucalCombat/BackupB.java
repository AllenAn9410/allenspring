package actucalCombat;

public class BackupB implements Runnable {
    private Backup backup;

    BackupB(Backup backup){
        this.backup = backup;
    }
    public void run() {
        backup.backupB();
    }
}
