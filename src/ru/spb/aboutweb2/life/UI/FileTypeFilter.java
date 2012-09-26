package ru.spb.aboutweb2.life.UI;

import javax.swing.filechooser.FileFilter;
import java.io.File;

/**
 * Created by IntelliJ IDEA.
 * User: Alex
 * Date: 23.09.2012
 * Time: 15:00:31
 * To change this template use File | Settings | File Templates.
 */
public class FileTypeFilter extends FileFilter {
    private String extension;
    private String description;

    public FileTypeFilter(String extension, String description) {
        this.extension = extension;
        this.description = description;
    }

    @Override
    public boolean accept(File file) {
        return file.isDirectory() || file.getName().endsWith(extension);
    }

    @Override
    public String getDescription() {
        return description + String.format(" (*%s)", extension);
    }
}
