import dayjs from 'dayjs/esm';

export interface ICentVehic {
  id: string;
  cdmac?: string | null;
  dateff?: dayjs.Dayjs | null;
  deccent?: number | null;
  decagenc?: number | null;
}

export type NewCentVehic = Omit<ICentVehic, 'id'> & { id: null };
