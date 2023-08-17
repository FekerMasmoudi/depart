import dayjs from 'dayjs/esm';

import { IExternalApi, NewExternalApi } from './external-api.model';

export const sampleWithRequiredData: IExternalApi = {
  id: 'ffaf42b5-aa72-4d10-99eb-55f679c59ae6',
};

export const sampleWithPartialData: IExternalApi = {
  id: '36914722-1185-4f13-8cbf-6224ccf62f27',
  idm: 'engage next New',
  name: 'black Fantastic payment',
  origin: 'azure extensible Computers',
  uritemplate: 'scalable Supervisor customized',
  schemaidt: 28928,
  lastupdatete: dayjs('2023-08-17'),
  entity: 'benchmark Home Meadow',
  countrowsreq: 35575,
  countrowsres: 28645,
  emergencycode: 'deposit morph Outdoors',
};

export const sampleWithFullData: IExternalApi = {
  id: '6b7e81a5-56ee-4420-922f-b61e49f070e0',
  idm: 'primary Rubber',
  name: 'Bike bypassing',
  status: 'azure revolutionary',
  comments: 'generate back',
  idschema: 84363,
  datecreatedt: dayjs('2023-08-16'),
  lastupdatedm: dayjs('2023-08-17'),
  origin: 'Holy',
  templateid: 93533,
  idmodule: 76925,
  uritemplate: 'Haiti turquoise',
  priority: 45670,
  schemaidt: 45291,
  createdatet: dayjs('2023-08-16'),
  lastupdatete: dayjs('2023-08-17'),
  entity: 'Administrator',
  parametre: 'Lira',
  countrowsreq: 77005,
  countrowsres: 56925,
  frequency: 'Account XSS',
  emergencycode: 'Pizza Pataca Practical',
  satausgetapi: false,
};

export const sampleWithNewData: NewExternalApi = {
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
