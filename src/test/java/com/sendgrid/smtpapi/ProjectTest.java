import org.junit.Test;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.Assert.assertEquals;

public class ProjectTest {

    // ,/Docker or docker/Docker
    @Test public void checkDockerExists() {
        assertEquals(true, Files.isDirectory(Paths.get("./Docker")));
    }

    // ./docker-compose.yml or ./docker/docker-compose.yml
    @Test public void checkDockerComposeExists() {
        assertEquals(true, new File("./docker-compose.yml").exists());
    }

    // ./.env_sample
    @Test public void checkEnvSampleExists() {
        assertEquals(true, new File("./.env_sample").exists());
    }

    // ./.gitignore
    @Test public void checkGitIgnoreExists() {
        assertEquals(true, new File("./.gitignore").exists());
    }

    // ./.travis.yml
    @Test public void checkTravisExists() {
        assertEquals(true, new File("./.travis.yml").exists());
    }

    // ./.codeclimate.yml
    @Test public void checkCodeClimateExists() {
        assertEquals(true, new File("./.codeclimate.yml").exists());
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

    // ./.github/ISSUE_TEMPLATE
    @Test public void checkIssuesTemplateExists() {
        assertEquals(true, new File("./.github/ISSUE_TEMPLATE").exists());
    }

    // ./LICENSE.md
    @Test public void checkLicenseExists() {
        assertEquals(true, new File("./LICENSE.md").exists());
    }

    // ./.github/PULL_REQUEST_TEMPLATE
    @Test public void checkPullRequestExists() {
        assertEquals(true, new File("./.github/PULL_REQUEST_TEMPLATE").exists());
    }

    // ./README.md
    @Test public void checkReadMeExists() {
        assertEquals(true, new File("./README.md").exists());
    }

    // ./TROUBLESHOOTING.md
    @Test public void checkTroubleShootingGuideExists() {
        assertEquals(true, new File("./TROUBLESHOOTING.md").exists());
    }

    // ./USAGE.md
    @Test public void checkUsageGuideExists() {
        assertEquals(true, new File("./USAGE.md").exists());
    }

    // ./USE_CASES.md
    @Test public void checkUseCases() {
        assertEquals(true, new File("./USE_CASES.md").exists());
    }
}
