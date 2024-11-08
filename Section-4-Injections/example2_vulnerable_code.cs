namespace CommandInjectionExample.Controllers
{
    public class HomeController : Controller
    {
        [HttpPost]
        public ActionResult LookupDomain(string domain)
        {
            // Vulnerable to Command Injection
            string command = $"nslookup {domain}";
            string output = ExecuteCommand(command);

            return Content(output);
        }

        private string ExecuteCommand(string command)
        {
            var process = new Process
            {
                StartInfo = new ProcessStartInfo
                {
                    FileName = "cmd.exe",
                    Arguments = $"/C {command}",
                    RedirectStandardOutput = true,
                    UseShellExecute = false,
                    CreateNoWindow = true
                }
            };
            process.Start();
            string result = process.StandardOutput.ReadToEnd();
            process.WaitForExit();

            return result;
        }
    }
}

