import dayjs from 'dayjs/esm';

export interface ITrafic {
  id: string;
  deccent?: number | null;
  decagenc?: number | null;
  dedated?: dayjs.Dayjs | null;
  ancien?: number | null;
  vldtrafic?: string | null;
  clotrafic?: string | null;
}

export type NewTrafic = Omit<ITrafic, 'id'> & { id: null };
