package actucalCombat;

public class Backup {
    private boolean prevIsA = true;

    synchronized public void backupA() {
        try {
            while (!prevIsA) {
                wait();
            }
            for (int i = 0; i < 5; i++) {
                System.out.println(" backup AAA " + i);
            }
            prevIsA = false;
            notifyAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    synchronized public void backupB() {
        try {
            while (prevIsA) {
                wait();
            }
            for (int i = 0; i < 5; i++) {
                System.out.println(" backup BBB " + i);
            }
            prevIsA = true;
            notifyAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Backup backup = new Backup();
        for (int i = 0; i < 4; i++) {
            new Thread(new BackupA(backup)).start();
            new Thread(new BackupB(backup)).start();

        }
    }
}
