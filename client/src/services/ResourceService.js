const departments = [
  {
    id: 1,
    departmentName: "Orthopedics",
    maintenanceMonth: 7,
    assignedTechnician: "Michael Ciccone"
  },
  {
    id: 2,
    departmentName: "Cardiology",
    maintenanceMonth: 9,
    assignedTechnician: "Tom Beerbower"
  },
  {
    id: 3,
    departmentName: "Radiology",
    maintenanceMonth: 11,
    assignedTechnician: "Boondock the Cat"
  },
  {
    id: 4,
    departmentName: "Pediatrics",
    maintenanceMonth: 7,
    assignedTechnician: "Michael Ciccone"
  },
  {
    id: 5,
    departmentName: "Neurology",
    maintenanceMonth: 9,
    assignedTechnician: "Tom Beerbower"
  },
  {
    id: 6,
    departmentName: "Oncology",
    maintenanceMonth: 11,
    assignedTechnician: "Boondock the Cat"
  },
  {
    id: 7,
    departmentName: "Emergency",
    maintenanceMonth: 7,
    assignedTechnician: "Michael Ciccone"
  },
  {
    id: 8,
    departmentName: "Dermatology",
    maintenanceMonth: 9,
    assignedTechnician: "Tom Beerbower"
  },
  {
    id: 9,
    departmentName: "Gastroenterology",
    maintenanceMonth: 11,
    assignedTechnician: "Boondock the Cat"
  },
  {
    id: 10,
    departmentName: "Urology",
    maintenanceMonth: 7,
    assignedTechnician: "Michael Ciccone"
  }
];

const models = [
  {
    id: 1,
    modelName: "Spectra 3.0",
    manufacturerName: "Nokia",
    maintenanceSchedule: "Biannual",
    description: "Ventilator"
  },
  {
    id: 2,
    modelName: "Yaris VX",
    manufacturerName: "Sony",
    maintenanceSchedule: "Monthly",
    description: "Infusion pump"
  },
  {
    id: 3,
    modelName: "LeMans Pro 9000",
    manufacturerName: "Huawei",
    maintenanceSchedule: "Biannual",
    description: "X-ray machine"
  },
  {
    id: 4,
    modelName: "AccuSign v3",
    manufacturerName: "Samsung",
    maintenanceSchedule: "Annual",
    description: "Vital signs monitor"
  },
  {
    id: 5,
    modelName: "B-Series Plus",
    manufacturerName: "LG",
    maintenanceSchedule: "Quarterly",
    description: "Anesthesia machine"
  },
];

const devices = [
  {
    id: 1001,
    serialNumber: "7ed2a",
    modelId: 1,
    owningDepartment: 1,
    installDate: "2002-08-05",
    pic: "medical-artificial-lung-ventilator-GqD1Y89-600.jpg"
  },
  {
    id: 1002,
    serialNumber: "708d2a1",
    modelId: 2,
    owningDepartment: 2,
    installDate: "2018-11-23",
    pic: "Medfusion_4000_pump_larger.png"
  },
  {
    id: 1003,
    serialNumber: "3a379ece",
    modelId: 3,
    owningDepartment: 3,
    installDate: "2014-01-21",
    pic: "Mobile-DR-Series500x500.png"
  },
  {
    id: 1004,
    serialNumber: "0f620cc45",
    modelId: 4,
    owningDepartment: 1,
    installDate: "2001-06-04",
    pic: "EMBIM50.jpg"
  },
  {
    id: 1005,
    serialNumber: "32751b",
    modelId: 5,
    owningDepartment: 2,
    installDate: "2021-01-05",
    pic: "A5-with-N17-753x1024-753x1024-1.jpg"
  },
  {
    id: 1006,
    serialNumber: "5ae7cb5",
    modelId: 1,
    owningDepartment: 3,
    installDate: "2011-04-18",
    pic: "medical-artificial-lung-ventilator-GqD1Y89-600.jpg"
  },
  {
    id: 1007,
    serialNumber: "4fc38d8",
    modelId: 2,
    owningDepartment: 1,
    installDate: "2006-10-01",
    pic: "Medfusion_4000_pump_larger.png"
  },
  {
    id: 1008,
    serialNumber: "ad7cc699",
    modelId: 3,
    owningDepartment: 2,
    installDate: "2020-06-01",
    pic: "Mobile-DR-Series500x500.png"
  },
  {
    id: 1009,
    serialNumber: "9a98812",
    modelId: 4,
    owningDepartment: 3,
    installDate: "2009-06-13",
    pic: "EMBIM50.jpg"
  },
];



const ResourceService = {
  getDepartments() {
    return departments
  },
  getDevices() {
    return devices;
  },
  getModels() {
    return models;
  },
  getDepartmentById(departmentId) {
    return this.getDepartments().find(department => department.id === departmentId);
  },
  getDevicesByDepartment(departmentId) {
    return this.getDevices().filter(device => device.owningDepartment === departmentId);
  }
};

export default ResourceService;
