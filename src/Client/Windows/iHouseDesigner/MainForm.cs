using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Windows.Forms;
using HeniHouse.Designer.Hosting;

namespace HeniHouse.Designer
{
    public partial class MainForm : Form
    {

        #region Member variables
        cDesignerMainControl mDesignerMain = null;

        #endregion

        public MainForm()
        {
            InitializeComponent();
            ShowTimeInfo();
            ShowDesigner();
        }

        private void timerTime_Tick(object sender, EventArgs e)
        {
            ShowTimeInfo();

        }

       

        private void rbDeviceConfiguration_CheckedChanged(object sender, EventArgs e)
        {
            if (rbDeviceConfiguration.Checked)
            {
                ShowDesigner();
            }
        }


        private void ShowTimeInfo()
        {
            lTime.Text = DateTime.Now.ToString("HH:mm:ss");
            lDate.Text = DateTime.Now.ToString("dd-MM-yyyy");
        }

        private void ShowDesigner()
        {
            if (mDesignerMain == null)
            {
                mDesignerMain = new cDesignerMainControl();
                pDialog.Controls.Add(mDesignerMain);
                mDesignerMain.Dock = DockStyle.Fill;
            }
            else
            {
                mDesignerMain.Visible = true;
                mDesignerMain.BringToFront();
            }
        }
    }
}
