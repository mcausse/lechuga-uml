package org.homs.lechugauml;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;

class ExportAsPngTest {

    protected List<File> processDirectory(File folder, Predicate<String> fileNamePredicate) {
        List<File> r = new ArrayList<>();
        for (final File fileEntry : Objects.requireNonNull(folder.listFiles())) {
            if (fileEntry.isDirectory()) {
                r.addAll(processDirectory(fileEntry, fileNamePredicate));
            } else {
                if (fileNamePredicate.test(fileEntry.getName())) {
                    System.out.println(fileEntry.getName());
                    r.add(fileEntry);
                }
            }
        }
        return r;
    }

    @Test
    void name() throws Exception {
        new File("./png").mkdir();
        var fs = processDirectory(new File("."), name -> name.endsWith(".uxf3"));
        for (var f : fs) {
            ExportAsPng.main(new String[]{
                    f.toString(),
                    "--zoom=2",
                    "--format=png",
                    "--output=./png/" + f.getName() + ".png"
            });
        }
    }

    @Test
    void welcome() throws Exception {
        ExportAsPng.main(new String[]{"diagrams_v3/lechugauml-showcase.uxf3", "--zoom=3", "--format=png", "--output=lechugauml-showcase.png", "--grid=false"});
    }

    @Test
    void houmls_white_paper() throws Exception {
        ExportAsPng.main(new String[]{"diagrams_v3/lechugauml-white-paper.uxf3", "--output=./lechugauml-white-paper.png"});
    }

}
