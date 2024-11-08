using System;
using System.Diagnostics;
using System.Web;
using System.Web.Mvc;

namespace CommandInjectionExample.Controllers
{
    public class HomeController : Controller
    {
        [HttpPost]
        public ActionResult LookupDomain(string domain)
        {
            if (IsValidDomain(domain))
            {
                string output = ExecuteNslookup(domain);
                return Content(output);
            }
            else
            {
                return Content("Invalid domain name");
            }
        }

        private bool IsValidDomain(string domain)
        {
            // Basic validation for domain names (you can enhance this as needed)
            return !string.IsNullOrWhiteSpace(domain) && Uri.CheckHostName(domain) != UriHostNameType.Unknown;
        }

        private string ExecuteNslookup(string domain)
        {
            var process = new Process
            {
                StartInfo = new ProcessStartInfo
                {
                    FileName = "nslookup",
                    Arguments = domain,
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

