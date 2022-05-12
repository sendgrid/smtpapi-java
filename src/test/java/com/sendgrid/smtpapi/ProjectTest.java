import org.junit.Test;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.Assert.assertEquals;

public class ProjectTest {

    // ./.env_sample
    @Test public void checkEnvSampleExists() {
        assertEquals(true, new File("./.env_sample").exists());
    }

    // ./.gitignore
    @Test public void checkGitIgnoreExists() {
        assertEquals(true, new File("./.gitignore").exists());
    }

    // ./CHANGELOG.md
    @Test public void checkChangelogExists() {
        assertEquals(true, new File("./CHANGELOG.md").exists());
    }

    // ./CODE_OF_CONDUCT.md
    @Test public void checkCodeOfConductExists() {
        assertEquals(true, new File("./CODE_OF_CONDUCT.md").exists());
    }

    // ./CONTRIBUTING.md
    @Test public void checkContributingGuideExists() {
        assertEquals(true, new File("./CONTRIBUTING.md").exists());
    }

    // ./LICENSE
    @Test public void checkLicenseExists() {
        assertEquals(true, new File("./LICENSE").exists());
    }

    // ./PULL_REQUEST_TEMPLATE.md
    @Test public void checkPullRequestExists() {
        assertEquals(true, new File("./PULL_REQUEST_TEMPLATE.md").exists());
    }

    // ./README.md
    @Test public void checkReadMeExists() {
        assertEquals(true, new File("./README.md").exists());
    }

    // ./TROUBLESHOOTING.md
    @Test public void checkTroubleShootingGuideExists() {
        assertEquals(true, new File("./TROUBLESHOOTING.md").exists());
    }

}
