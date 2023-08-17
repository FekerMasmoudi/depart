import dayjs from 'dayjs/esm';

export interface IExternalApi {
  id: string;
  idm?: string | null;
  name?: string | null;
  status?: string | null;
  comments?: string | null;
  idschema?: number | null;
  datecreatedt?: dayjs.Dayjs | null;
  lastupdatedm?: dayjs.Dayjs | null;
  origin?: string | null;
  templateid?: number | null;
  idmodule?: number | null;
  uritemplate?: string | null;
  priority?: number | null;
  schemaidt?: number | null;
  createdatet?: dayjs.Dayjs | null;
  lastupdatete?: dayjs.Dayjs | null;
  entity?: string | null;
  parametre?: string | null;
  countrowsreq?: number | null;
  countrowsres?: number | null;
  frequency?: string | null;
  emergencycode?: string | null;
  satausgetapi?: boolean | null;
}

export type NewExternalApi = Omit<IExternalApi, 'id'> & { id: null };
