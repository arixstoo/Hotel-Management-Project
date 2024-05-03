package Controller.Exceptions;

public class isValidPassword {
    public isValidPassword(char[] password) throws Exception_text {
        String passwordStr = new String(password);
        if (passwordStr.length() < 8) {
            throw new Exception_text("Le mot de passe doit contenir au moins 8 caractères.");
        }
        if (!passwordStr.matches(".*[a-z].*")) {
            throw new Exception_text("Le mot de passe doit contenir au moins une lettre minuscule.");
        }
        if (!passwordStr.matches(".*[A-Z].*")) {
            throw new Exception_text("Le mot de passe doit contenir au moins une lettre majuscule.");
        }
        if (!passwordStr.matches(".*\\W.*")) {
            throw new Exception_text("Le mot de passe doit contenir au moins un caractère spécial.");
        }
    }
}
